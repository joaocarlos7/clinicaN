package dev.clinican.dto;

import dev.clinican.entity.enums.ConsultationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultationDto(
        UUID id,
        UUID doctorId,
        UUID patientId,
        LocalDateTime consultationDate,
        ConsultationStatus consultationStatus,
        String reason,
        String note,
        UUID createdBy) {
}
