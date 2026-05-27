package dev.clinican.repository;

import dev.clinican.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    List<Patient> findByNameContainingIgnoreCase(String name);
    Optional<Patient> findByCpfContainingIgnoreCase(String cpf);


}
