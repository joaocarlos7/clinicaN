package dev.clinican.exception;

import java.util.UUID;

public class PrescriptionNotFound extends RuntimeException {
    public PrescriptionNotFound(UUID id) {
        super("Prescription not found with id " + id);
    }
}
