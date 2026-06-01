package dev.clinican.controller;


import dev.clinican.dto.ConsultationHistoryDto;
import dev.clinican.service.ConsultationHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/consultationHistory")
public class ConsultationHistoryController {

    private final ConsultationHistoryService consultationHistoryService;

    public ConsultationHistoryController(ConsultationHistoryService consultationHistoryService) {
        this.consultationHistoryService = consultationHistoryService;
    }


    @GetMapping("/id/{id}")
    ConsultationHistoryDto getConsultationHistoryById(@PathVariable UUID id){
        return consultationHistoryService.findById(id);
    }

    @GetMapping("/patientName/{patientName}")
    List<ConsultationHistoryDto> getConsultationHistoryByPatientName(@PathVariable String patientName) {
        return consultationHistoryService.findByPatientUserName(patientName);
    }

    @GetMapping("/doctorName/{doctorName}")
    List<ConsultationHistoryDto> getConsultationHistoryByDoctorName(@PathVariable String doctorName) {
        return consultationHistoryService.findByDoctorUserName(doctorName);
    }

    @GetMapping("/findAll")
    List<ConsultationHistoryDto> findAll() {
        return consultationHistoryService.findAll();
    }


}
