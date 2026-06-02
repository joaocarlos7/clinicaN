package dev.clinican.exception;

public class PatientCpfNotFound extends RuntimeException {
    public PatientCpfNotFound(String cpf) {
        super("Patient not found with cpf: " + cpf);
    }
}
