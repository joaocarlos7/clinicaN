package dev.clinican.dto;

import dev.clinican.entity.TbUser;
import dev.clinican.entity.enums.StatusType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultationHistoryDto(
        UUID id,
        UUID consultationId,
        StatusType statusBefore,
        StatusType statusNew,
        LocalDateTime changedAt,
        TbUser changedBy) {
}