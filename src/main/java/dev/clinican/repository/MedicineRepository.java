package dev.clinican.repository;

import dev.clinican.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
}
