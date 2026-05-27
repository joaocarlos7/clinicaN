package dev.clinican.controller;

import dev.clinican.dto.ConsultationDto;
import dev.clinican.dto.PrescriptionDto;
import dev.clinican.service.ConsultationService;
import dev.clinican.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    // Find By Id
    @GetMapping("/{id}")
    ConsultationDto getById(@PathVariable UUID id){
        return consultationService.findById(id);
    }

    // Find by Observation
    @GetMapping
    List<ConsultationDto> getByObservation(@RequestParam String observation){
        return consultationService.findByObservation(observation);
    }

    // Find by Doctor Name
    @GetMapping
    List<ConsultationDto> getByDoctorName(@RequestParam String name){
        return consultationService.findByDoctorName(name);
    }

    // Find by Patient Name
    @GetMapping
    List<ConsultationDto> getByPatientName(@RequestParam String name){
        return consultationService.findByPatientName(name);
    }

    // Create
    @PostMapping
    ConsultationDto createConsultation(@RequestBody ConsultationDto consultationDto) {
        return consultationService.create(consultationDto);
    }

    // Update
    @PutMapping("/{id}")
    ConsultationDto updateConsultation(@PathVariable UUID id, @RequestBody ConsultationDto consultationDto) {
        return consultationService.update(id,  consultationDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    void deleteConsultation(@PathVariable UUID id) {
        consultationService.delete(id);
    }


}
