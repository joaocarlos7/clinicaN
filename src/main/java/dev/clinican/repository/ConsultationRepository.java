package dev.clinican.repository;

import dev.clinican.entity.Consultation;
import dev.clinican.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ConsultationRepository extends JpaRepository<Consultation, UUID> {


    @Query
    List<Consultation> findByObservationContainingIgnoreCase(String observation);
    List<Consultation> findByDoctorNameContainingIgnoreCase(String name);
    List<Consultation> findByPatientNameContainingIgnoreCase(String name);

}
