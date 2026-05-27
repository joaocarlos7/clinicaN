package dev.clinican.repository;

import dev.clinican.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    List<Doctor> findByDoctorNameContainingIgnoreCase(String name);
    Optional<Doctor> findByCrmContainingIgnoreCase(String crm);
}
