package dev.clinican.service;


import dev.clinican.dto.MedicineDto;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Medicine;
import dev.clinican.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }



    // Converter Methods

    // DTO to Entity
    private Medicine toEntity(MedicineDto medicineDto) {

        Medicine medicine = new Medicine();
        medicine.setName(medicineDto.name());
        medicine.setDescription(medicineDto.description());
        medicine.setActiveIngredient(medicineDto.activeIngredient());

        return medicine;

    }

    // Entity to DTO
    private MedicineDto toDto(Medicine medicine) {
        return new MedicineDto(
                medicine.getId(),
                medicine.getName(),
                medicine.getDescription(),
                medicine.getActiveIngredient()
        );

    }


    // Public Methods
    // Create
    public MedicineDto create(MedicineDto medicineDto) {
        Medicine medicine = toEntity(medicineDto);
        Medicine saveMedicine = medicineRepository.save(medicine);

        return toDto(saveMedicine);
    }

    // Update
    public MedicineDto update(UUID id, MedicineDto medicineDto) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found" + medicineDto.id()));

        medicine.setName(medicineDto.name());
        medicine.setDescription(medicineDto.description());
        medicine.setActiveIngredient(medicineDto.activeIngredient());

        return toDto(medicineRepository.save(toEntity(medicineDto)));



    }

    // Delete
    public void delete(UUID id) {
        medicineRepository.deleteById(id);
    }

    // Find By ID
    public MedicineDto findById(UUID id) {

        return medicineRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Medicine not found" + id));
    }

    // Find by Name
    public List<MedicineDto> findByName(String name) {

        return medicineRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDto)
                .toList();
    }
}
