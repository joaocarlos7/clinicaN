package dev.clinican.service;


import dev.clinican.dto.PrescriptionDto;
import dev.clinican.entity.*;
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

    // Public Methods

    // Create
    public PrescriptionDto create(PrescriptionDto prescriptionDto) {
        Prescription prescription = prescriptionMapping.toEntity(prescriptionDto);
        Prescription savePrescription = prescriptionRepository.save(prescription);

        return prescriptionMapping.toDto(savePrescription);
    }

    // Update
    public PrescriptionDto update(UUID id, PrescriptionDto prescriptionDto) {

        Prescription prescription = prescriptionRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Prescription not found" + id));

        Doctor doctor = doctorRepository.findById(prescriptionDto.doctorID())
                        .orElseThrow(() -> new RuntimeException("Doctor not found" + prescriptionDto.doctorID()));

        Patient patient = patientRepository.findById(prescriptionDto.patientID())
                        .orElseThrow(() -> new RuntimeException("Patient not found" + prescriptionDto.patientID()));

        Consultation consultation = consultationRepository.findById(prescriptionDto.consultationID())
                        .orElseThrow(() -> new RuntimeException("Consultation not found" + prescriptionDto.consultationID()));


        prescription.setConsultation(consultation);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setObservation(prescriptionDto.observation());
        prescription.setCreatedAt(prescriptionDto.createdAt());


        return prescriptionMapping.toDto(prescriptionRepository.save(prescriptionMapping.toEntity(prescriptionDto)));

    }

    // Delete
    public void delete(UUID id) {
        prescriptionRepository.deleteById(id);
    }

    // Find by ID
    public PrescriptionDto findById(UUID id) {
        return prescriptionRepository.findById(id)
                .map(prescriptionMapping::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    // Find by Observation
    public List<PrescriptionDto> findByObservation(String observation) {
        return prescriptionRepository.findByObservationContainingIgnoreCase(observation)
                .stream() // Take the list one by one
                .map(prescriptionMapping::toDto)// Convert in Dto
                .toList(); // List
    }

    // Find by created date
    public List<PrescriptionDto> findByCreatedAt(LocalDateTime localDate) {
        return prescriptionRepository.findByCreatedAtContainingIgnoreCase(localDate)
                .stream() // Take the list one by one
                .map(prescriptionMapping::toDto)// Convert in Dto
                .toList(); // List
    }
}
