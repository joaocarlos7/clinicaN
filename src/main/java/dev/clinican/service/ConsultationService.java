package dev.clinican.service;

import dev.clinican.dto.ConsultationDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.repository.ConsultationRepository;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public ConsultationService(ConsultationRepository consultationRepository,
                               DoctorRepository doctorRepository,
                               PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }




    // DTO to Entity (Entry)
    private Consultation toEntity(ConsultationDto consultationDto) {
        Doctor doctor = doctorRepository.findById(consultationDto.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found" + consultationDto.doctorId()));
        Patient patient = patientRepository.findById(consultationDto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found" + consultationDto.patientId()));

        Consultation consultation = new Consultation();
        consultation.setDoctor(doctor);
        consultation.setConsultationDate(consultationDto.consultationDate());
        consultation.setPatient(patient);
        consultation.setConsultationStatus(consultationDto.consultationStatus());
        consultation.setReason(consultationDto.reason());
        consultation.setNote(consultationDto.note());
        consultation.setCreatedBy(consultationDto.createdBy());


        return consultation;
    }
    // Entity to DTO (Exit)
    private ConsultationDto toDto(Consultation consultation) {
        return new ConsultationDto(
                consultation.getId(),
                consultation.getDoctor().getId(),
                consultation.getPatient().getId(),
                consultation.getConsultationDate(),
                consultation.getConsultationStatus(),
                consultation.getReason(),
                consultation.getNote(),
                consultation.getCreatedBy()
        );
    }


    // Public Methods
    public ConsultationDto create(ConsultationDto consultationDto) {
        return toDto(consultationRepository.save(toEntity(consultationDto)));
    }
    public ConsultationDto update(UUID id, ConsultationDto consultationDto) {
        Doctor doctor = doctorRepository.findById(consultationDto.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found" + consultationDto.doctorId()));
        Patient patient = patientRepository.findById(consultationDto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found" + consultationDto.patientId()));

        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found" + consultationDto.id()));
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);
        consultation.setConsultationDate(consultationDto.consultationDate());
        consultation.setReason(consultationDto.reason());
        consultation.setNote(consultationDto.note());
        consultation.setConsultationStatus(consultationDto.consultationStatus());
        consultation.setCreatedBy(consultationDto.createdBy());


        return toDto(consultationRepository.save(consultation));




    }
    public void delete(UUID id) {
        consultationRepository.deleteById(id);
    }
    public List<ConsultationDto> findByObservation(String observation) {
        return consultationRepository.findByObservationContainingIgnoreCase(observation)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
    public List<ConsultationDto> findByDoctorName(String name) {
        return consultationRepository
                .findByDoctorNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
    public List<ConsultationDto> findByPatientName(String name) {
        return consultationRepository
                .findByPatientNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDto)
                .toList();
    }


}
