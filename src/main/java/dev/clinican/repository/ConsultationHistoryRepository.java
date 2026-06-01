package dev.clinican.repository;

import dev.clinican.entity.ConsultationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsultationHistoryRepository extends JpaRepository<ConsultationHistory, UUID> {

    Optional <ConsultationHistory> findByConsultationId(UUID consultationHistoryId);
    List<ConsultationHistory> findByConsultationDoctorUserNameContainingIgnoreCase(String name);
    List<ConsultationHistory> findByConsultationPatientUserNameContainingIgnoreCase(String name);
    List<ConsultationHistory> findAll();
}
