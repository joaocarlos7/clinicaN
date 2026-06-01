package dev.clinican.repository;

import dev.clinican.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ConsultationRepository extends JpaRepository<Consultation, UUID> {


    List<Consultation> findByNoteContainingIgnoreCase(String observation);
    List<Consultation> findByDoctorUserNameContainingIgnoreCase(String name);
    List<Consultation> findByPatientUserNameContainingIgnoreCase(String name);


}
