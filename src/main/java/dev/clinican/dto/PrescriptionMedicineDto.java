package dev.clinican.dto;

import java.util.UUID;

public record PrescriptionMedicineDto(
        UUID id,
        UUID prescriptionId,
        UUID medicineId,
        String dose,
        String frequency,
        int numberOfDays
) {
}
