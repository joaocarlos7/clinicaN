package dev.clinican.service;


import dev.clinican.dto.PrescriptionDto;
import dev.clinican.entity.*;
import dev.clinican.exception.ConsultationNotFound;
import dev.clinican.exception.DoctorNotFoundException;
import dev.clinican.exception.PatientNotFound;
import dev.clinican.exception.PrescriptionNotFound;
import dev.clinican.mapping.PrescriptionMapping;
import dev.clinican.repository.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionMapping prescriptionMapping;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               ConsultationRepository consultationRepository,
                               PatientRepository patientRepository,
                               DoctorRepository doctorRepository,
                               PrescriptionMapping prescriptionMapping) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.prescriptionMapping = prescriptionMapping;
    }

    public PrescriptionDto create(PrescriptionDto prescriptionDto) {


        Prescription prescription = prescriptionMapping.toEntity(prescriptionDto);
        Prescription savePrescription = prescriptionRepository.save(prescription);

        return prescriptionMapping.toDto(savePrescription);
    }

    public PrescriptionDto update(UUID id, PrescriptionDto prescriptionDto) {

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new PrescriptionNotFound(id));

        Doctor doctor = doctorRepository.findById(prescriptionDto.doctorID())
                .orElseThrow(() -> new DoctorNotFoundException(id));

        Patient patient = patientRepository.findById(prescriptionDto.patientID())
                .orElseThrow(() -> new PatientNotFound(id));

        Consultation consultation = consultationRepository.findById(prescriptionDto.patientID())
                .orElseThrow(()-> new ConsultationNotFound(id));

            prescription.setConsultation(consultation);
            prescription.setDoctor(doctor);
            prescription.setPatient(patient);
            prescription.setObservation(prescriptionDto.observation());
            prescription.setCreatedAt(prescriptionDto.createdAt());


            return prescriptionMapping.toDto(prescriptionRepository.save(prescriptionMapping.toEntity(prescriptionDto)));
    }

    public void delete(UUID id) {
        if(!prescriptionRepository.existsById(id)){
            throw new PrescriptionNotFound(id);
        }
        prescriptionRepository.deleteById(id);
    }

    public PrescriptionDto findById(UUID id) {
        return prescriptionRepository.findById(id)
                .map(prescriptionMapping::toDto)
                .orElseThrow(() -> new PrescriptionNotFound(id));
    }

    public List<PrescriptionDto> findByObservation(String observation) {
        return prescriptionRepository.findByObservationContainingIgnoreCase(observation)
                .stream()
                .map(prescriptionMapping::toDto)
                .toList();
    }

    public List<PrescriptionDto> findByCreatedAt(LocalDateTime localDate) {
        return prescriptionRepository.findByCreatedAtContainingIgnoreCase(localDate)
                .stream()
                .map(prescriptionMapping::toDto)
                .toList();
    }
}
