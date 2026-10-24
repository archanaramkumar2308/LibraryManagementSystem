package service;

import exception.LibraryException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Holds books, members and transactions and enforces the business rules. */
public class Library {
    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<BorrowTransaction> borrows = new ArrayList<>();
    private final List<ReturnTransaction> returns = new ArrayList<>();
    private int bookNo = 101, memberNo = 201, txnNo = 1001;

    public Book addBook(String title, String author, int copies) {
        Book book = new Book("B" + bookNo++, title, new Author(author), copies);
        books.put(book.getBookId(), book);
        return book;
    }

    public Member addMember(String name, boolean premium) {
        String id = "M" + memberNo++;
        Member m = premium ? new PremiumMember(id, name) : new Member(id, name);
        members.put(id, m);
        return m;
    }

    private Book findBook(String id) throws LibraryException {
        Book b = books.get(id.toUpperCase());
        if (b == null) throw new LibraryException("No book found with ID '" + id + "'.");
        return b;
    }

    private Member findMember(String id) throws LibraryException {
        Member m = members.get(id.toUpperCase());
        if (m == null) throw new LibraryException("No member found with ID '" + id + "'.");
        return m;
    }

    /** Borrow: checks the member's limit and the stock, then reduces stock. */
    public BorrowTransaction borrowBook(String memberId, String bookId) throws LibraryException {
        Member member = findMember(memberId);
        Book book = findBook(bookId);
        if (member.getActiveBorrows().size() >= member.getMaxBooks()) {
            throw new LibraryException(member.getName() + " (" + member.getType()
                    + ") can borrow only " + member.getMaxBooks() + " books at a time.");
        }
        book.issueCopy();
        BorrowTransaction t = new BorrowTransaction("BR" + txnNo++, book, member, LocalDate.now());
        member.getActiveBorrows().add(t);
        borrows.add(t);
        return t;
    }

    /** Return: finds the active borrow, increases stock and calculates the fine. */
    public ReturnTransaction returnBook(String memberId, String bookId, int daysAfterBorrow) throws LibraryException {
        Member member = findMember(memberId);
        Book book = findBook(bookId);
        for (BorrowTransaction t : member.getActiveBorrows()) {
            if (t.getBook() == book) {
                ReturnTransaction r = new ReturnTransaction("RT" + txnNo++, t,
                        t.getBorrowDate().plusDays(daysAfterBorrow));
                book.receiveCopy();
                member.getActiveBorrows().remove(t);
                returns.add(r);
                return r;
            }
        }
        throw new LibraryException(member.getName() + " has not borrowed '" + book.getTitle() + "'.");
    }

    public List<Book> getAvailableBooks() {
        List<Book> list = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getAvailableCopies() > 0) list.add(b);
        }
        return list;
    }
}
