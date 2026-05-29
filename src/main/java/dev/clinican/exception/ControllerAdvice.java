package dev.clinican.exception;

@ControllerAdvice
public class ControllerAdvice extends RuntimeException {
    public ControllerAdvice(String message) {
        super(message);
    }
}
