package model;

import exception.BookNotAvailableException;

/** A book with stock tracking. A Book has an Author (aggregation). */
public class Book {
    private final String bookId;
    private final String title;
    private final Author author;
    private final int totalCopies;
    private int availableCopies;

    public Book(String bookId, String title, Author author, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    /** Rule 2: borrowing reduces stock. Rule 1: only if available. */
    public void issueCopy() throws BookNotAvailableException {
        if (availableCopies == 0) {
            throw new BookNotAvailableException(title);
        }
        availableCopies--;
    }

    /** Rule 2: returning increases stock. */
    public void receiveCopy() { availableCopies++; }
}
