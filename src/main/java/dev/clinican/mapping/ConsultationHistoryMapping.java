package dev.clinican.mapping;


import dev.clinican.dto.ConsultationDto;
import dev.clinican.dto.ConsultationHistoryDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.ConsultationHistory;
import org.springframework.stereotype.Component;

@Component
public class ConsultationHistoryMapping {

    public ConsultationHistoryDto toDto(ConsultationHistory consultationHistory) {
        return new ConsultationHistoryDto(
                consultationHistory.getId(),
                consultationHistory.getConsultation().getId(),
                consultationHistory.getStatusBefore(),
                consultationHistory.getStatusNew(),
                consultationHistory.getChangedAt(),
                consultationHistory.getChangedBy().getId()
        );
    }
}
