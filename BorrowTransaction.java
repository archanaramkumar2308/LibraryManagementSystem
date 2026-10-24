package model;

import java.time.LocalDate;

/** One borrowing. Links directly to a Book and a Member (rule 5). */
public class BorrowTransaction {
    private final String id;
    private final Book book;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public BorrowTransaction(String id, Book book, Member member, LocalDate borrowDate) {
        this.id = id;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(member.getLoanDays());
    }

    public String getId() { return id; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
}
