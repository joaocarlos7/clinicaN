package dev.clinican.controller;

import dev.clinican.dto.ConsultationDto;
import dev.clinican.service.ConsultationService;
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

    // Find
    @GetMapping("/{id}")
    ConsultationDto findById(@PathVariable UUID id){
        return consultationService.findById(id);
        }

    @GetMapping("/search")
    List<ConsultationDto> search(
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String observation
    ) {
        if (doctorName != null) return consultationService.findByDoctorName(doctorName);
        if (patientName != null) return consultationService.findByPatientName(patientName);
        if (observation != null) return consultationService.findByObservation(observation);
        return List.of();
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
