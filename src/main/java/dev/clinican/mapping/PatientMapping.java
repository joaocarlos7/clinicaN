package dev.clinican.mapping;

import dev.clinican.dto.PatientDto;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Component;

@Component
public class PatientMapping {

    private final TbUserRepository tbUserRepository;

    public PatientMapping(
            TbUserRepository tbUserRepository
    ) {
        this.tbUserRepository = tbUserRepository;
    }


    public Patient toEntity(PatientDto patientDto) {
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

    public PatientDto toDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getUser().getId(),
                patient.getCpf(),
                patient.getBornDay(),
                patient.getAddress(),
                patient.getPhoneNumber()
        );
    }
}
