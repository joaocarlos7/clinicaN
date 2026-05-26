package dev.clinican.repository;

import dev.clinican.entity.Consultation;
import dev.clinican.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    List<Medicine> findByNameContainingIgnoreCase(String name);
}
