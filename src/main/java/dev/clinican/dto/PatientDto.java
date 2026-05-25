package dev.clinican.dto;

import dev.clinican.entity.TbUser;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientDto(
        UUID id,
        TbUser user,
        String cpf,
        LocalDateTime bornDay,
        String phoneNumber,
        String address
) {
}
