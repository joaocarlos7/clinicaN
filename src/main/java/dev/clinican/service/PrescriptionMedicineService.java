package dev.clinican.service;


import dev.clinican.dto.PrescriptionDto;
import dev.clinican.dto.PrescriptionMedicineDto;
import dev.clinican.entity.*;
import dev.clinican.repository.MedicineRepository;
import dev.clinican.repository.PrescriptionMedicineRepository;
import dev.clinican.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionMedicineService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    public PrescriptionMedicineService(PrescriptionRepository prescriptionRepository,
                                       PrescriptionMedicineRepository prescriptionMedicineRepository,
                                       MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMedicineRepository = prescriptionMedicineRepository;
        this.medicineRepository = medicineRepository;
    }



    // Conversion Methods

    // DTO to Entity
    private PrescriptionMedicine toEntity(PrescriptionMedicineDto prescriptionMedicineDto) {
        Prescription prescription = prescriptionRepository.findById(prescriptionMedicineDto.prescriptionId())
                .orElseThrow(()-> new RuntimeException("Prescription not found" + prescriptionMedicineDto.prescriptionId()));

        Medicine medicine = medicineRepository.findById(prescriptionMedicineDto.medicineId())
                .orElseThrow(()-> new RuntimeException("Medicine not found" + prescriptionMedicineDto.medicineId()));

        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
        prescriptionMedicine.setPrescription(prescription);
        prescriptionMedicine.setMedicine(medicine);
        prescriptionMedicine.setDose(prescriptionMedicineDto.dose());
        prescriptionMedicine.setFrequency(prescriptionMedicineDto.frequency());
        prescriptionMedicine.setNumberOfDays(prescriptionMedicineDto.numberOfDays());


        return prescriptionMedicine;
    }

    // Entity to DTO
    private PrescriptionMedicineDto toDto(PrescriptionMedicine prescriptionMedicine) {
        return new PrescriptionMedicineDto(
                prescriptionMedicine.getId(),
                prescriptionMedicine.getPrescription().getId(),
                prescriptionMedicine.getMedicine().getId(),
                prescriptionMedicine.getDose(),
                prescriptionMedicine.getFrequency(),
                prescriptionMedicine.getNumberOfDays()

        );
    }


    // Public Methods

    // Create
    public PrescriptionMedicineDto create(PrescriptionMedicineDto prescriptionMedicineDto) {
        PrescriptionMedicine prescriptionMedicine = toEntity(prescriptionMedicineDto);
        PrescriptionMedicine savePrescriptionMedicine = prescriptionMedicineRepository.save(prescriptionMedicine);

        return toDto(savePrescriptionMedicine);
    }

    // Update
    public PrescriptionMedicineDto update(UUID id, PrescriptionMedicineDto prescriptionMedicineDto) {
        Medicine medicine = medicineRepository.findById(prescriptionMedicineDto.medicineId())
                .orElseThrow(() -> new RuntimeException("Consultation not found" + prescriptionMedicineDto.medicineId()));
        Prescription prescription = prescriptionRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Prescription not found" + id));

        PrescriptionMedicine prescriptionMedicine = prescriptionMedicineRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Medicine not found" + id));
        prescriptionMedicine.setPrescription(prescription);
        prescriptionMedicine.setMedicine(medicine);
        prescriptionMedicine.setDose(prescriptionMedicineDto.dose());
        prescriptionMedicine.setFrequency(prescriptionMedicineDto.frequency());
        prescriptionMedicine.setNumberOfDays(prescriptionMedicineDto.numberOfDays());


        return toDto(prescriptionMedicineRepository.save(toEntity(prescriptionMedicineDto)));

    }

    // Delete
    public void delete(UUID id) {
        prescriptionMedicineRepository.deleteById(id);
    }

    // Find by ID
    public PrescriptionMedicineDto findById(UUID id) {
        return prescriptionMedicineRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }




}
