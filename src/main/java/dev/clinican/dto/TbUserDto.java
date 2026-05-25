package dev.clinican.dto;

import dev.clinican.entity.enums.RoleType;

import java.time.LocalDateTime;
import java.util.UUID;

public record TbUserDto(
        UUID id,
        String name,
        String email,
        RoleType role,
        LocalDateTime createdAt,
        Boolean active
) {
}
