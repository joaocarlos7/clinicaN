package dev.clinican.exception;

public class DoctorAlreadyExistisException extends RuntimeException {
    public DoctorAlreadyExistisException(Integer crm) {

        super("Doctor with crm " + crm + " already exists");
    }
}
