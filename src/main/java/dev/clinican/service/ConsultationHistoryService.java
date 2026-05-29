package dev.clinican.service;


import dev.clinican.entity.Consultation;
import dev.clinican.entity.ConsultationHistory;
import dev.clinican.entity.TbUser;
import dev.clinican.entity.enums.ConsultationStatus;
import dev.clinican.repository.ConsultationHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service

public class ConsultationHistoryService {

    private final ConsultationHistoryRepository consultationHistoryRepository;

    public ConsultationHistoryService(ConsultationHistoryRepository consultationHistoryRepository) {
        this.consultationHistoryRepository = consultationHistoryRepository;
    }

    public ConsultationHistory recordChange(
            Consultation consultation,
            ConsultationStatus statusBefore,
            ConsultationStatus statusNew,
            TbUser changeBy
    ) {
        ConsultationHistory history = ConsultationHistory.builder()
                .consultation(consultation)
                .statusBefore(statusBefore)
                .statusNew(statusNew)
                .changedBy(changeBy)
                .changedAt(LocalDateTime.now())
                .build();

        return consultationHistoryRepository.save(history);
    }

    // ConsultationByID
    public List<ConsultationHistory> findByConsultationId(UUID consultationId) {
        return consultationHistoryRepository.findByConsultationId(consultationId);
    }



}
