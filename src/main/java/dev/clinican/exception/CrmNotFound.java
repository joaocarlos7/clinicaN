package dev.clinican.exception;

public class CrmNotFound extends RuntimeException {
    public CrmNotFound(Integer crm) {
        super("Doctor not found with crm " + crm);
    }
}
