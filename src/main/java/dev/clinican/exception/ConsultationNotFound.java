package dev.clinican.exception;

import java.util.UUID;

public class ConsultationNotFound extends RuntimeException {
    public ConsultationNotFound(UUID id) {
        super("Consultation not found with id " + id);
    }
}
