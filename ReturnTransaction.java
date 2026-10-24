package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** One return. Calculates the fine for late returns (rule 4). */
public class ReturnTransaction {
    private final String id;
    private final BorrowTransaction borrow;
    private final LocalDate returnDate;
    private final long daysLate;
    private final double fine;

    public ReturnTransaction(String id, BorrowTransaction borrow, LocalDate returnDate) {
        this.id = id;
        this.borrow = borrow;
        this.returnDate = returnDate;
        this.daysLate = Math.max(0, ChronoUnit.DAYS.between(borrow.getDueDate(), returnDate));
        this.fine = daysLate * borrow.getMember().getFinePerDay();
    }

    public String getId() { return id; }
    public BorrowTransaction getBorrow() { return borrow; }
    public LocalDate getReturnDate() { return returnDate; }
    public long getDaysLate() { return daysLate; }
    public double getFine() { return fine; }
}
