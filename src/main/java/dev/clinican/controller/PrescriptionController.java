package dev.clinican.controller;

import dev.clinican.dto.PrescriptionDto;
import dev.clinican.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prescription")

public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    // FindById
    @GetMapping("/{id}")
    PrescriptionDto getPrescriptionById(@PathVariable UUID id) {
        return prescriptionService.findById(id);
    }

    // FindByPrescription
    @GetMapping
    List<PrescriptionDto> findByObservation(@RequestParam String observation) {
        return prescriptionService.findByObservation(observation);
    }

    // FindByCreatedAt
    List<PrescriptionDto> findByCreatedAt(@RequestParam LocalDateTime createdAt) {
        return prescriptionService.findByCreatedAt(createdAt);
    }

    // CreatePrescription
    @PostMapping
    PrescriptionDto createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        return prescriptionService.create(prescriptionDto);
    }

    // UpdatePrescription
    @PutMapping("/{id}")
    PrescriptionDto updatePrescription(@PathVariable UUID id, @RequestBody PrescriptionDto prescriptionDto) {
        return prescriptionService.update(id, prescriptionDto);
    }

    // DeletePrescription
    @DeleteMapping("/{id}")
    void deletePrescription(@PathVariable UUID id) {
        prescriptionService.delete(id);
    }
}