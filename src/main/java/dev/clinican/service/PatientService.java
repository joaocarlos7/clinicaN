package dev.clinican.service;


import dev.clinican.dto.DoctorDto;
import dev.clinican.dto.PatientDto;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.PatientRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final TbUserRepository tbUserRepository;
    private final PatientRepository patientRepository;


    public PatientService(TbUserRepository tbUserRepository, PatientRepository patientRepository) {
        this.tbUserRepository = tbUserRepository;
        this.patientRepository = patientRepository;
    }

    // Convert Methods

    // DTO to Entity (Entry)
    private Patient toEntity(PatientDto patientDto) {
        TbUser user = tbUserRepository.findById(patientDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setAddress(patientDto.address());
        patient.setCpf(patientDto.cpf());
        patient.setBornDay(patientDto.bornDay());
        patient.setPhoneNumber(String.valueOf(patientDto.phoneNumber()));

        return patient;
    }

    // Entity to DTO (Exit)
    private PatientDto toDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getUser().getId(),
                patient.getCpf(),
                patient.getBornDay(),
                patient.getAddress(),
                patient.getPhoneNumber()
        );
    }

    // Public Methods

    // Create
    public PatientDto create(PatientDto patientDto) {
        Patient patient = toEntity(patientDto);
        Patient savePatient = patientRepository.save(patient);
        return toDto(savePatient);
    }

    // Update
    public PatientDto update(UUID id, PatientDto patientDto) {
        TbUser user = tbUserRepository.findById(patientDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found" + patientDto.id()));
        patient.setUser(user);
        patient.setCpf(patientDto.cpf());
        patient.setBornDay(patientDto.bornDay());
        patient.setAddress(patientDto.address());
        patient.setPhoneNumber(patientDto.phoneNumber());


        return toDto(patientRepository.save(patient));




    }

    // Delete
    public void delete(UUID id) {
        patientRepository.deleteById(id);
    }

    // Find by ID
    public PatientDto findById(UUID id) {
        return patientRepository.findById(id)
                .map(this:: toDto)
                .orElseThrow(()-> new RuntimeException("Patient not found" + id));
    }

    // List by Name
    public List<PatientDto> findByName(String name) {
        return patientRepository
                .findByUserNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }

    // List by CPF
    public PatientDto findByCpf(String cpf) {
        return patientRepository.findByCpfContainingIgnoreCase(cpf)
                .map(this::toDto)
                .orElseThrow(()-> new RuntimeException("Patient not found" + cpf));

    }

}
