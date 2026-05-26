package dev.clinican.service;


import dev.clinican.dto.MedicineDto;
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
    public MedicineDto create(MedicineDto medicineDto) {
        return toDto(medicineRepository.save(toEntity(medicineDto)));
    }
    public MedicineDto update(UUID id, MedicineDto medicineDto) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found" + medicineDto.id()));

        medicine.setName(medicineDto.name());
        medicine.setDescription(medicineDto.description());
        medicine.setActiveIngredient(medicineDto.activeIngredient());

        return toDto(medicineRepository.save(toEntity(medicineDto)));



    }
    public void delete(UUID id) {
        medicineRepository.deleteById(id);
    }
    public MedicineDto findById(UUID id) {

        return medicineRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Medicine not found" + id));
    }
    public List<MedicineDto> findByName(String name) {

        return medicineRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDto)
                .toList();
    }
}
