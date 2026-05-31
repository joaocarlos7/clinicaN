package dev.clinican.controller;

import dev.clinican.dto.DoctorDto;
import dev.clinican.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/doctor")

public class DoctorController {

    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Find
    @GetMapping("/{id}")
    DoctorDto findById(@PathVariable UUID id) {
        return doctorService.findById(id);
    }

    // List By Name
    @GetMapping("/name/")
    List<DoctorDto> search(@RequestParam(required = false) String name) {
        return doctorService.findByDoctorName(name);
    }

    @GetMapping("/crm/{crm}")
    DoctorDto findByCrm(@PathVariable Integer crm) {
        return doctorService.findByCrm(crm);
    }

    // Create
    @PostMapping
    DoctorDto create(@RequestBody DoctorDto doctorDto) {
        return doctorService.create(doctorDto);
    }

    // Update
    @PutMapping("/{id}")
    DoctorDto update(@PathVariable UUID id, @RequestBody DoctorDto doctorDto) {
        return doctorService.update(id, doctorDto);
    }

    // Delete
    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {
        doctorService.delete(id);
    }
}
