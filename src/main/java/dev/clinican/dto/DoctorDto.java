package dev.clinican.dto;


import java.util.UUID;

public record DoctorDto(
        UUID id,
        UUID userId,
        Long crm,
        String speciality,
        Long phoneNumber
) {
}
