package dev.clinican.repository;

import dev.clinican.entity.Consultation;
import dev.clinican.entity.ConsultationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConsultationHistoryRepository extends JpaRepository<ConsultationHistory, UUID> {

    ConsultationHistory findByConsultationId(UUID consultationId);
    List<ConsultationHistory> findByDoctorUserNameContainingIgnoreCase(String name);
    List<ConsultationHistory> findByPatientUserNameContainingIgnoreCase(String name);
    List<ConsultationHistory> findAll();
}
