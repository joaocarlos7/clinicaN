package dev.clinican.repository;

import dev.clinican.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    List<Prescription> findByObservationContainingIgnoreCase(String observation);
    List<Prescription> findByCreatedAtContainingIgnoreCase(LocalDateTime createdAt);
}
