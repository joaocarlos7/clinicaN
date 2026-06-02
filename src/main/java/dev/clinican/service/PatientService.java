package dev.clinican.service;


import dev.clinican.dto.PatientDto;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.exception.PatientAlreadyExistsException;
import dev.clinican.exception.PatientCpfNotFound;
import dev.clinican.exception.PatientNotFound;
import dev.clinican.exception.UserNotFoundException;
import dev.clinican.mapping.PatientMapping;
import dev.clinican.repository.PatientRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final TbUserRepository tbUserRepository;
    private final PatientRepository patientRepository;
    private final PatientMapping patientMapping;


    public PatientService(TbUserRepository tbUserRepository,
                          PatientRepository patientRepository,
                          PatientMapping patientMapping) {
        this.tbUserRepository = tbUserRepository;
        this.patientRepository = patientRepository;
        this.patientMapping = patientMapping;
    }

    // Public Methods

    // Create
    public PatientDto create(PatientDto patientDto) {
        Patient patient = patientMapping.toEntity(patientDto);
        if (patientRepository.existsByCpf(patientDto.cpf())) {
            throw new PatientAlreadyExistsException(patientDto.cpf());
        }
        try {
            Patient savePatient = patientRepository.save(patient);
            return patientMapping.toDto(savePatient);
        } catch (PatientAlreadyExistsException e) {
            throw new PatientAlreadyExistsException(patientDto.cpf());
        }
    }

    // Update
    public PatientDto update(UUID id, PatientDto patientDto) {
        TbUser user = tbUserRepository.findById(patientDto.userId())
                .orElseThrow(() -> new UserNotFoundException(patientDto.userId()));

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFound(id));

        boolean patientAlreadyExists = patientRepository.existsByCpf(patientDto.cpf())
                && !patient.getCpf().equals(patientDto.cpf());

        if (patientAlreadyExists) {
            throw new PatientAlreadyExistsException(patientDto.cpf());
        }
        patient.setUser(user);
        patient.setCpf(patientDto.cpf());
        patient.setBornDay(patientDto.bornDay());
        patient.setAddress(patientDto.address());
        patient.setPhoneNumber(patientDto.phoneNumber());

        return patientMapping.toDto(patientRepository.save(patient));
    }

    // Delete
    public void delete(UUID id) {
        if(!patientRepository.existsById(id)) {
            throw new PatientNotFound(id);
        }
        patientRepository.deleteById(id);
    }

    // Find by ID
    public PatientDto findById(UUID id) {
        return patientRepository.findById(id)
                .map(patientMapping:: toDto)
                .orElseThrow(()-> new PatientNotFound(id));
    }

    // List by Name
    public List<PatientDto> findByName(String name) {
        return patientRepository
                .findByUserNameContainingIgnoreCase(name)
                .stream()
                .map(patientMapping::toDto)
                .toList();
    }

    // List by CPF
    public PatientDto findByCpf(String cpf) {
        return patientRepository.findByCpfContainingIgnoreCase(cpf)
                .map(patientMapping::toDto)
                .orElseThrow(()-> new PatientCpfNotFound(cpf));

    }

}
