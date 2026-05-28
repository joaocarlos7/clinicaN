package dev.clinican.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PrescriptionDto(
        UUID id,
        UUID consultationID,
        UUID medicineID,
        UUID patientID,
        UUID doctorID,
        LocalDateTime createdAt,
        String observation
) {
}
