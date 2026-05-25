package dev.clinican.service;


import dev.clinican.dto.PrescriptionDto;
import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.Prescription;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.ConsultationRepository;
import dev.clinican.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ConsultationRepository consultationRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               ConsultationRepository consultationRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
    }


    // Conversion Methods
    // DTO to Entity (Entry)
    private Prescription toEntity(PrescriptionDto prescriptionDto) {
        Consultation consultation = consultationRepository.findById(prescriptionDto.consultationID())
                .orElseThrow(() -> new RuntimeException("Consultation not found" + prescriptionDto.consultationID()));

        Prescription prescription = new Prescription();
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
                prescription.getCreatedAt(),
                prescription.getObservation());
    }



    // Public Methods
    public PrescriptionDto create(PrescriptionDto user) {
        return toDto(prescriptionRepository.save(toEntity(user)));
    }
    public PrescriptionDto update(UUID id, PrescriptionDto prescriptionDto) {
        Consultation consultation = consultationRepository.findById(prescriptionDto.consultationID())
                .orElseThrow(() -> new RuntimeException("Consultation not found" + prescriptionDto.consultationID()));

        Prescription prescription = prescriptionRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Prescription not found" + id));
        prescription.setConsultation(consultation);
        prescription.setObservation(prescriptionDto.observation());
        prescription.setCreatedAt(prescriptionDto.createdAt());

        return toDto(prescriptionRepository.save(toEntity(prescriptionDto)));

    }
    public void delete(UUID id) {
        prescriptionRepository.deleteById(id);
    }
    public PrescriptionDto findById(UUID id) {
        return prescriptionRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }
    public List<PrescriptionDto> findByObservation(String observation) {
        return prescriptionRepository.findByObservationContainingIgnoreCase(observation)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
    public List<PrescriptionDto> findByCreatedAt(LocalDateTime localDate) {
        return prescriptionRepository.findByCreatedAtContainingIgnoreCase(localDate)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
}
