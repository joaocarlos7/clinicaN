package dev.clinican.entity;


import dev.clinican.entity.enums.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "consultation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ConsultationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_before", columnDefinition = "status_type")
    private StatusType statusBefore;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_new", nullable = false, columnDefinition = "status_type")
    private StatusType statusNew;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    private TbUser changedBy;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;
}