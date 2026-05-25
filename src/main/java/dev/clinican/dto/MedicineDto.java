package dev.clinican.dto;

import java.util.UUID;

public record MedicineDto(
        UUID id,
        String name,
        String activeIngredient,
        String description
) {
}
