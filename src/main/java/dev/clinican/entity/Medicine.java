package dev.clinican.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "active_ingredient", nullable = false)
    private String activeIngredient;

    @Column(nullable = false)
    private String description;
}
