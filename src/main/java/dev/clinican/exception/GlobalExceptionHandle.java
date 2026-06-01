package dev.clinican.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(DoctorAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleDoctorAlreadyExists(DoctorAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", e.getMessage()));
    }
    public ResponseEntity<Map<String, String>> handleDoctorNotFound(DoctorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", e.getMessage()));
    }
    public ResponseEntity<Map<String, String>> handlePatientAlreadyExists(PatientAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", e.getMessage()));
    }
    public ResponseEntity<Map<String, String>> handleUserAlreadyExists(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", e.getMessage()));
    }
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
    }



}

