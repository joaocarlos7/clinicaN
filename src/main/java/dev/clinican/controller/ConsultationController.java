package dev.clinican.controller;

import dev.clinican.dto.ConsultationDto;
import dev.clinican.service.ConsultationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/consultation")
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

    @GetMapping("/doctorName/{doctorName}")
    List<ConsultationDto> findByDoctorName(@PathVariable(required = false) String doctorName) {
        if (doctorName != null) return consultationService.findByDoctorName(doctorName);
        return List.of();
    }

    @GetMapping("/patientName/{patientName}")
    List<ConsultationDto> findByPatientName(@PathVariable(required = false) String patientName) {
        if (patientName != null) return consultationService.findByPatientName(patientName);
        return List.of();
    }

    @GetMapping("/observation/{observation}")
    List<ConsultationDto> findByObservation(@PathVariable(required = false) String observation) {
        if (observation != null) return consultationService.findByObservation(observation);
        return List.of();
    }

    // Create
    @PostMapping("/create")
    ConsultationDto createConsultation(@RequestBody ConsultationDto consultationDto) {
        return consultationService.create(consultationDto);
    }

    // Update
    @PutMapping("/{id}")
    ConsultationDto updateConsultation(@PathVariable UUID id, @RequestBody ConsultationDto consultationDto) {
        return consultationService.update(id,  consultationDto);
    }

    // Update Status
    @PutMapping("/updateStatus/{id}")
    ConsultationDto updateConsultationStatus(@PathVariable UUID id, @RequestBody ConsultationDto consultationDto) {
        return consultationService.updateStatus(id, consultationDto);
    }


    // Delete
    @DeleteMapping("/{id}")
    void deleteConsultation(@PathVariable UUID id) {
        consultationService.delete(id);
    }


}
