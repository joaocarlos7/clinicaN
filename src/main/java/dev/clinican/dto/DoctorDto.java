package dev.clinican.dto;


import java.util.UUID;

public record DoctorDto(
        UUID id,
        UUID userId,
        Integer crm,
        String speciality,
        Integer phoneNumber
) {
}
