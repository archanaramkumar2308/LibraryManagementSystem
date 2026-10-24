package exception;

/** Thrown when a book has no copies left (inheritance: extends LibraryException). */
public class BookNotAvailableException extends LibraryException {
    public BookNotAvailableException(String title) {
        super("'" + title + "' is not available (0 copies left).");
    }
}
