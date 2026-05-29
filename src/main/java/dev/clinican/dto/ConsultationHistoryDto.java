package dev.clinican.dto;

import dev.clinican.entity.enums.ConsultationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultationHistoryDto(
        UUID id,
        UUID consultationId,
        ConsultationStatus statusBefore,
        ConsultationStatus statusNew,
        LocalDateTime changedAt,
        UUID changedById) {
}


