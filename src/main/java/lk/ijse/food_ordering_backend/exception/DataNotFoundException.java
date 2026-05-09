package lk.ijse.food_ordering_backend.exception;

// Thrown when a requested record is missing.
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
