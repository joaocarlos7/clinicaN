package dev.clinican.repository;

import dev.clinican.entity.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine, UUID> {
}
