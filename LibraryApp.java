package main;

import exception.LibraryException;
import model.*;
import service.Library;
import util.InputHelper;

import java.util.Scanner;

/** Menu-driven Library Management System. */
public class LibraryApp {

    public static void main(String[] args) {
        InputHelper in = new InputHelper(new Scanner(System.in));
        Library library = new Library();
        System.out.println("=== Library Management System ===");

        while (true) {
            System.out.println("\n----------- LIBRARY MENU -----------");
            System.out.println("1. Add Book                2. Add Member");
            System.out.println("3. Borrow Book             4. Return Book");
            System.out.println("5. Display Available Books 6. Exit");
            int choice = in.readInt("Enter your choice (1-6): ", 1, 6);

            try {
                if (choice == 1) {
                    String title = in.readText("Book title: ");
                    String author = in.readText("Author name: ");
                    int copies = in.readInt("Number of copies (1-100): ", 1, 100);
                    System.out.println("Book added. ID: " + library.addBook(title, author, copies).getBookId());
                } else if (choice == 2) {
                    String name = in.readText("Member name: ");
                    int type = in.readInt("Type (1 = Regular, 2 = Premium): ", 1, 2);
                    Member m = library.addMember(name, type == 2);
                    System.out.println("Member added. ID: " + m.getMemberId() + " (" + m.getType() + ": "
                            + m.getMaxBooks() + " books, " + m.getLoanDays() + " days, fine Rs." + m.getFinePerDay() + "/day)");
                } else if (choice == 3) {
                    String memberId = in.readText("Member ID: ");
                    String bookId = in.readText("Book ID: ");
                    printBorrowReceipt(library.borrowBook(memberId, bookId));
                } else if (choice == 4) {
                    String memberId = in.readText("Member ID: ");
                    String bookId = in.readText("Book ID: ");
                    int days = in.readInt("Returned how many days after borrowing? (0-365): ", 0, 365);
                    printReturnReceipt(library.returnBook(memberId, bookId, days));
                } else if (choice == 5) {
                    displayAvailableBooks(library);
                } else {
                    System.out.println("Thank you. Goodbye!");
                    return;
                }
            } catch (LibraryException e) {
                System.out.println("ERROR: " + e.getMessage());   // program continues
            }
        }
    }

    private static void printBorrowReceipt(BorrowTransaction t) {
        System.out.println("--------- BORROW RECEIPT " + t.getId() + " ---------");
        System.out.println("Member : " + t.getMember().getName() + " (" + t.getMember().getType() + ")");
        System.out.println("Book   : " + t.getBook().getTitle() + " (" + t.getBook().getBookId() + ")");
        System.out.println("Borrowed: " + t.getBorrowDate() + "   Due: " + t.getDueDate());
        System.out.println("Stock  : " + t.getBook().getAvailableCopies() + "/" + t.getBook().getTotalCopies() + " available");
    }

    private static void printReturnReceipt(ReturnTransaction r) {
        BorrowTransaction t = r.getBorrow();
        System.out.println("--------- RETURN RECEIPT " + r.getId() + " ---------");
        System.out.println("Member : " + t.getMember().getName() + " (" + t.getMember().getType() + ")");
        System.out.println("Book   : " + t.getBook().getTitle() + " (" + t.getBook().getBookId() + ")");
        System.out.println("Due: " + t.getDueDate() + "   Returned: " + r.getReturnDate());
        System.out.printf("Fine   : Rs.%.2f (%d days late x Rs.%.2f)%n", r.getFine(), r.getDaysLate(), t.getMember().getFinePerDay());
        System.out.println("Stock  : " + t.getBook().getAvailableCopies() + "/" + t.getBook().getTotalCopies() + " available");
    }

    private static void displayAvailableBooks(Library library) {
        if (library.getAvailableBooks().isEmpty()) {
            System.out.println("No books are available right now.");
            return;
        }
        System.out.printf("%-6s %-20s %-18s %s%n", "ID", "Title", "Author", "Available");
        for (Book b : library.getAvailableBooks()) {
            System.out.printf("%-6s %-20s %-18s %d/%d%n", b.getBookId(), b.getTitle(),
                    b.getAuthor().getName(), b.getAvailableCopies(), b.getTotalCopies());
        }
    }
}
