package dev.clinican.repository;

import dev.clinican.entity.Doctor;
import dev.clinican.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    List<Doctor> findByNameContainingIgnoreCase(String name);
}
