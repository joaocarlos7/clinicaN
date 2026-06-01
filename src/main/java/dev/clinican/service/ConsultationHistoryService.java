package dev.clinican.service;


import dev.clinican.dto.ConsultationDto;
import dev.clinican.dto.ConsultationHistoryDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.ConsultationHistory;
import dev.clinican.entity.TbUser;
import dev.clinican.entity.enums.ConsultationStatus;
import dev.clinican.mapping.ConsultationHistoryMapping;
import dev.clinican.repository.ConsultationHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ConsultationHistoryService {

    private final ConsultationHistoryRepository consultationHistoryRepository;
    public final ConsultationHistoryMapping consultationHistoryMapping;

    public ConsultationHistoryService(ConsultationHistoryRepository consultationHistoryRepository,
                                      ConsultationHistoryMapping consultationHistoryMapping) {
        this.consultationHistoryRepository = consultationHistoryRepository;
        this.consultationHistoryMapping = consultationHistoryMapping;
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



    public List<ConsultationHistoryDto> findAll() {
        return consultationHistoryRepository.findAll()
                .stream()
                .map(consultationHistoryMapping::toDto)
                .toList();
    }


}
