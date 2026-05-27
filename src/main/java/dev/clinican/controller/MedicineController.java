package dev.clinican.controller;


import dev.clinican.dto.MedicineDto;
import dev.clinican.entity.Medicine;
import dev.clinican.service.MedicineService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    // Find By ID
    @GetMapping("/{id}")
    MedicineDto getById(@PathVariable UUID id) {
        return medicineService.findById(id);
    }

    // Find By Name
    @GetMapping
    List<MedicineDto> findByName(@RequestBody String name) {
        return medicineService.findByName(name);
    }

    // Create
    @PostMapping
    MedicineDto create(@RequestBody MedicineDto medicineDto) {
        return medicineService.create(medicineDto);
    }

    // Update
    @PutMapping("/{id}")
    MedicineDto update(@PathVariable UUID id, @RequestBody MedicineDto medicineDto) {
        return medicineService.update(id, medicineDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        medicineService.delete(id);
    }

}
