package dev.clinican.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prescription")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Prescription {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn(name = "medicine", nullable = false)
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "patient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor", nullable = false)
    private Doctor doctor;

    private String observation;


}
