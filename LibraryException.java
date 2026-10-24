package exception;

/** Custom exception for all library business-rule errors. */
public class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}
