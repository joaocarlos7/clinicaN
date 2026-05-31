package dev.clinican.controller;

import dev.clinican.dto.PrescriptionDto;
import dev.clinican.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/prescription")

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

    // Find by Prescription / CreatedAt
    @GetMapping
    List<PrescriptionDto> search(
            @RequestParam(required = false) String observation,
            @RequestParam(required = false) LocalDateTime createdAt
    ){
        if(observation != null) return prescriptionService.findByObservation(observation);
        if(createdAt != null) return prescriptionService.findByCreatedAt(createdAt);
        return List.of();
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