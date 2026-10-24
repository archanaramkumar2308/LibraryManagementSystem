# Library Management System (Java OOP Mini Project)

**Name:** Archana R  
**Roll / Register No:** 717825I204  
**Course:** 23CSR306 Java Programming – Assignment II (OOP Mini Project)  
**Specification:** 2 – Library Management System  
**College:** Karpagam College of Engineering

## Problem Statement
Design and implement a Java console application for a Library Management System that manages books, members,
borrowing and returns. The system follows object-oriented principles and keeps proper track of book availability.

## Features (Menu)
1. Add Book
2. Add Member (Regular or Premium)
3. Borrow Book – prints a borrow receipt
4. Return Book – prints a return receipt and fine (if late)
5. Display Available Books
6. Exit

## Business Rules Implemented
1. A book can be borrowed only if a copy is available.
2. Borrowing reduces stock; returning increases stock.
3. Premium members borrow more books and for longer (Regular: 2 books / 14 days, Premium: 5 books / 21 days).
4. Fines apply for late returns (Regular Rs. 2/day, Premium Rs. 1/day).
5. Every borrow and return transaction links directly to a book (and the member).

## OOP Concepts Used
| Concept | Where |
|---|---|
| Encapsulation | Private fields with getters in `Book`, `Member`, `Author`, transactions |
| Inheritance | `PremiumMember extends Member`; custom exceptions extend `LibraryException` |
| Polymorphism | `getMaxBooks()`, `getLoanDays()`, `getFinePerDay()` overridden in `PremiumMember` |
| Aggregation | `Book` has an `Author`; `Member` has a list of `BorrowTransaction`; `Library` holds books and members |
| Exception handling | Custom exceptions (`LibraryException`, `BookNotAvailableException`) + `try-catch` in `LibraryApp`; input validated in `InputHelper` |

## Project Structure
```
src/
 ├── model/      Author, Book, Member, PremiumMember, BorrowTransaction, ReturnTransaction
 ├── service/    Library
 ├── exception/  LibraryException, BookNotAvailableException
 ├── util/       InputHelper
 └── main/       LibraryApp  (contains main method)
```

## How to Compile and Run
**Command line** (Java 8 or above), from the project root:
```
javac -d out $(find src -name "*.java")
java -cp out main.LibraryApp
```
On Windows (PowerShell/CMD):
```
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
java -cp out main.LibraryApp
```
**Eclipse:** File > Import > Existing Projects (or create a Java Project and copy the `src` folder), then right-click
`main/LibraryApp.java` > Run As > Java Application.

## Sample Run
```
Enter your choice (1-6): 4
Member ID: M201
Book ID: B101
Returned how many days after borrowing? (0-365): 20
--------- RETURN RECEIPT RT1004 ---------
Member : Priya (Regular)
Book   : Java Programming (B101)
Fine   : Rs.12.00 (6 days late x Rs.2.00)
Stock  : 2/2 available
```
