package dev.clinican.dto;

import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.entity.enums.StatusType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultationDto(
        UUID id,
        UUID doctorId,
        UUID patientId,
        LocalDateTime consultationDate,
        StatusType consultationStatus,
        String reason,
        String note,
        TbUser createdBy) {
}
