package dev.clinican.entity;


import dev.clinican.entity.enums.RoleType;
import dev.clinican.entity.enums.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class TbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Boolean active;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "role_type")
    private RoleType roleType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
