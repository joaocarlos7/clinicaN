package dev.clinican.exception;

import java.util.UUID;

public class PatientNotFound extends RuntimeException {
    public PatientNotFound(UUID id) {
        super("Patient not found with id " + id);
    }

}
