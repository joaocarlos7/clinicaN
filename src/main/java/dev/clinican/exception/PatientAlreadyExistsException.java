package dev.clinican.exception;

public class PatientAlreadyExistsException extends RuntimeException {
    public PatientAlreadyExistsException(String cpf) {

        super("Patient with cpf " + cpf + " already exists");
    }
}
