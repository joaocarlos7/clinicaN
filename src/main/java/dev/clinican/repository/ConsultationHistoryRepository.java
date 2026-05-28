package dev.clinican.repository;

import dev.clinican.entity.ConsultationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConsultationHistoryRepository extends JpaRepository<ConsultationHistory, UUID> {

    List<ConsultationHistory> findByConsultationId(UUID consultationId);

}
