package lk.ijse.food_ordering_backend.exception;

// Thrown when a duplicate record is found.
public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String message) {
        super(message);
    }
}
