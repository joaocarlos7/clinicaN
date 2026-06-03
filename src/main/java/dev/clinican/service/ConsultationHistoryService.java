package dev.clinican.service;


import dev.clinican.dto.ConsultationHistoryDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.ConsultationHistory;
import dev.clinican.entity.TbUser;
import dev.clinican.entity.enums.ConsultationStatus;
import dev.clinican.exception.ConsultationNotFound;
import dev.clinican.mapping.ConsultationHistoryMapping;
import dev.clinican.repository.ConsultationHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    public ConsultationHistoryDto findById(UUID id) {
        return consultationHistoryRepository.findByConsultationId(id)
                .stream()
                .map(consultationHistoryMapping::toDto)
                .findFirst()
                .orElseThrow(() -> new ConsultationNotFound(id));
    }

    public List<ConsultationHistoryDto> findByDoctorUserName(String name) {
        return consultationHistoryRepository.findByConsultationDoctorUserNameContainingIgnoreCase(name)
                .stream()
                .map(consultationHistoryMapping::toDto)
                .toList();
    }

    public List<ConsultationHistoryDto> findByPatientUserName(String name) {
        return consultationHistoryRepository.findByConsultationPatientUserNameContainingIgnoreCase(name)
                .stream()
                .map(consultationHistoryMapping::toDto)
                .toList();
    }


}
