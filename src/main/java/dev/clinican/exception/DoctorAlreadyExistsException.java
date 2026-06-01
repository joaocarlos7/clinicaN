package dev.clinican.exception;


public class DoctorAlreadyExistsException extends RuntimeException {
    public DoctorAlreadyExistsException(Integer crm) {

        super("Doctor with crm " + crm + " already exists");
    }

}
