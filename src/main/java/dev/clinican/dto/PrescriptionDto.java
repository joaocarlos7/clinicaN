package dev.clinican.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PrescriptionDto(
        UUID id,
        UUID consultationID,
        LocalDateTime createdAt,
        String observation
) {
}
