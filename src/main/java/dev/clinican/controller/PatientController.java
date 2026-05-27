package dev.clinican.controller;

import dev.clinican.dto.DoctorDto;
import dev.clinican.dto.PatientDto;
import dev.clinican.service.DoctorService;
import dev.clinican.service.PatientService;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.UUID;

@RestController

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    // Find by ID
    @GetMapping("/{id}")
    PatientDto findById(@PathVariable UUID id) {
        return patientService.findById(id);
    }

    // Find by Name
    @GetMapping
    List<PatientDto> findByName(String name) {
        return patientService.findByName(name);
    }

    // Find By CPF
    @GetMapping
    PatientDto findByCpf(String cpf) {
        return patientService.findByCpf(cpf);
    }

    // Create
    @PostMapping
    PatientDto save(@RequestBody PatientDto patientDto) {
        return patientService.create(patientDto);
    }

    // Update
    @PutMapping("/{id}")
    PatientDto update(@PathVariable UUID id, @RequestBody PatientDto patientDto) {
        return patientService.update(id, patientDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        patientService.delete(id);
    }
}
