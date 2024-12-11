package at.htlleonding.library;

public class SchoolLibraryException extends RuntimeException {
    public SchoolLibraryException(String message) {
        super(message);
    }

    public SchoolLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}
