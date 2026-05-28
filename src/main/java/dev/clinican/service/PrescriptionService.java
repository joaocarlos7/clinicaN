package dev.clinican.service;


import dev.clinican.dto.PrescriptionDto;
import dev.clinican.entity.*;
import dev.clinican.repository.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ConsultationRepository consultationRepository;
    private final MedicineRepository medicineRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               ConsultationRepository consultationRepository,
                               PatientRepository patientRepository,
                               DoctorRepository doctorRepository,
                               MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.medicineRepository = medicineRepository;
    }


    // Conversion Methods

    // DTO to Entity (Entry)
    private Prescription toEntity(PrescriptionDto prescriptionDto) {
        Consultation consultation = consultationRepository.findById(prescriptionDto.consultationID())
                .orElseThrow(() -> new RuntimeException("Consultation not found" + prescriptionDto.consultationID()));
        Medicine medicine =  medicineRepository.findById(prescriptionDto.medicineID())
                .orElseThrow(() -> new RuntimeException("Medicine not found" + prescriptionDto.medicineID()));
        Patient patient = patientRepository.findById(prescriptionDto.patientID())
                .orElseThrow(() -> new RuntimeException("Patient not found" + prescriptionDto.patientID()));
        Doctor doctor = doctorRepository.findById(prescriptionDto.doctorID())
                .orElseThrow(() -> new RuntimeException("Doctor not found" + prescriptionDto.doctorID()));

        Prescription prescription = new Prescription();
        prescription.setMedicine(medicine);
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescription.setConsultation(consultation);
        prescription.setObservation(prescriptionDto.observation());
        prescription.setCreatedAt(prescriptionDto.createdAt());

        return prescription;
    }
    // Entity to DTO (Exit)
    private PrescriptionDto toDto(Prescription prescription) {
        return new PrescriptionDto(
                prescription.getId(),
                prescription.getConsultation().getId(),
                prescription.getMedicine().getId(),
                prescription.getDoctor().getId(),
                prescription.getPatient().getId(),
                prescription.getCreatedAt(),
                prescription.getObservation());
    }

    // Public Methods

    // Create
    public PrescriptionDto create(PrescriptionDto prescriptionDto) {
        Prescription prescription = toEntity(prescriptionDto);
        Prescription savePrescription = prescriptionRepository.save(prescription);

        return toDto(savePrescription);
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

        Medicine medicine = medicineRepository.findById(prescriptionDto.medicineID())
                        .orElseThrow(() -> new RuntimeException("Medicine not found" + prescriptionDto.medicineID()));


        prescription.setConsultation(consultation);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setMedicine(medicine);
        prescription.setObservation(prescriptionDto.observation());
        prescription.setCreatedAt(prescriptionDto.createdAt());


        return toDto(prescriptionRepository.save(toEntity(prescriptionDto)));

    }

    // Delete
    public void delete(UUID id) {
        prescriptionRepository.deleteById(id);
    }

    // Find by ID
    public PrescriptionDto findById(UUID id) {
        return prescriptionRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    // Find by Observation
    public List<PrescriptionDto> findByObservation(String observation) {
        return prescriptionRepository.findByObservationContainingIgnoreCase(observation)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }

    // Find by created date
    public List<PrescriptionDto> findByCreatedAt(LocalDateTime localDate) {
        return prescriptionRepository.findByCreatedAtContainingIgnoreCase(localDate)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
}
