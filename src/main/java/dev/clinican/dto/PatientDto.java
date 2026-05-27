package dev.clinican.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public record PatientDto(
        UUID id,
        UUID userId,
        String cpf,
        LocalDateTime bornDay,
        String phoneNumber,
        String address
) {
}
