package lk.ijse.food_ordering_backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Converts backend exceptions into simple HTTP error responses.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle missing data errors.
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDataNotFound(DataNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Handle duplicate entry errors.
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEntry(DuplicateEntryException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // Handle invalid login credentials.
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    // Handle unexpected runtime errors.
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    // Build a structured error response body.
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
