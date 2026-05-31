package dev.clinican.service;

import dev.clinican.dto.ConsultationDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.entity.enums.ConsultationStatus;
import dev.clinican.mapping.ConsultationMapping;
import dev.clinican.repository.ConsultationRepository;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.PatientRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ConsultationService {


    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ConsultationHistoryService consultationHistoryService;
    private final TbUserRepository tbUserRepository;
    private final ConsultationMapping consultationMapping;

    public ConsultationService(ConsultationRepository consultationRepository,
                               DoctorRepository doctorRepository,
                               PatientRepository patientRepository,
                               ConsultationHistoryService consultationHistoryService,
                               TbUserRepository tbUserRepository,
                               ConsultationMapping consultationMapping) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.consultationHistoryService = consultationHistoryService;
        this.tbUserRepository = tbUserRepository;
        this.consultationMapping = consultationMapping;
    }

    // Public Methods

    // Create
    public ConsultationDto create(ConsultationDto consultationDto) {

        Consultation consultation = consultationMapping.toEntity(consultationDto);
        Consultation savedConsultation = consultationRepository.save(consultation);

        consultationHistoryService.recordChange(
                savedConsultation,
                null,
                savedConsultation.getConsultationStatus(),
                savedConsultation.getCreatedBy()
        );

        return consultationMapping.toDto(savedConsultation);

    }

    //  Update
    public ConsultationDto update(UUID id, ConsultationDto consultationDto) {

        Doctor doctor = doctorRepository.findById(consultationDto.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found" + consultationDto.doctorId()));
        Patient patient = patientRepository.findById(consultationDto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found" + consultationDto.patientId()));

        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found" + consultationDto.id()));
        TbUser tbUser = tbUserRepository.findById(consultationDto.createdBy())
                .orElseThrow(() -> new RuntimeException("TbUser not found" + consultationDto.createdBy()));

        ConsultationStatus oldStatus = consultation.getConsultationStatus();

        consultation.setDoctor(doctor);
        consultation.setPatient(patient);
        consultation.setConsultationDate(consultationDto.consultationDate());
        consultation.setReason(consultationDto.reason());
        consultation.setNote(consultationDto.note());
        consultation.setConsultationStatus(consultationDto.consultationStatus());
        consultation.setCreatedBy(tbUser);


        Consultation savedConsultation = consultationRepository.save(consultation);
        consultationHistoryService.recordChange(
                savedConsultation,
                oldStatus,
                savedConsultation.getConsultationStatus(),
                savedConsultation.getCreatedBy()
        );
        return consultationMapping.toDto(savedConsultation);
    }

    //  Delete
    public void delete(UUID id) {
        consultationRepository.deleteById(id);
    }

    // List By ID
    public ConsultationDto findById(UUID id) {
        return consultationRepository.findById(id)
                .map(consultationMapping::toDto)
                .orElseThrow(() -> new RuntimeException("Consultation not found" + id));
    }

    //  List By Observation
    public List<ConsultationDto> findByObservation(String observation) {
        return consultationRepository.findByNoteContainingIgnoreCase(observation)
                .stream() // Take the list one by one
                .map(consultationMapping::toDto)// Convert in Dto
                .toList(); // List
    }

    //  List by Doctor Name
    public List<ConsultationDto> findByDoctorName(String name) {
        return consultationRepository
                .findByDoctorUserNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(consultationMapping::toDto)// Convert in Dto
                .toList(); // List
    }

    //  List by Patient Name
    public List<ConsultationDto> findByPatientName(String name) {
        return consultationRepository
                .findByPatientUserNameContainingIgnoreCase(name)
                .stream()
                .map(consultationMapping::toDto)
                .toList();
    }

    public List<ConsultationDto> findAll() {
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapping::toDto)
                .toList();
    }

}
