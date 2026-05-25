package dev.clinican.dto;

import dev.clinican.entity.TbUser;

import java.util.UUID;

public record DoctorDto(
        UUID id,
        UUID userId,
        Long crm,
        String name,
        String speciality,
        Long phoneNumber
) {
}
