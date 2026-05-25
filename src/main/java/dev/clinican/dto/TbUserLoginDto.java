package dev.clinican.dto;

import dev.clinican.entity.enums.RoleType;

import java.time.LocalDateTime;

public record TbUserLoginDto(
        String name,
        String email,
        String password,
        RoleType role,
        LocalDateTime createdAt,
        Boolean active
) {
}
