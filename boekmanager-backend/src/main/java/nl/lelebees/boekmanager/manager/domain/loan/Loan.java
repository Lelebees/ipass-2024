package nl.lelebees.boekmanager.manager.domain.loan;

import nl.lelebees.boekmanager.manager.domain.Entity;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.time.LocalDateTime;
import java.util.UUID;

public class Loan extends Entity<UUID> {
    private Book book;
    private Loaner loaner;
    private LocalDateTime loanedAt;
    private LocalDateTime toReturnAt;
    private LocalDateTime returnedAt;

    public Loan(Book book, Loaner loaner, LocalDateTime loanedAt, LocalDateTime toReturnAt, LocalDateTime returnedAt) {
        this(UUID.randomUUID(), book, loaner, loanedAt, toReturnAt, returnedAt);
    }

    protected Loan(UUID id, Book book, Loaner loaner, LocalDateTime loanedAt, LocalDateTime toReturnAt, LocalDateTime returnedAt) {
        super(id);
        this.book = book;
        this.loaner = loaner;
        if (loanedAt == null) {
            throw new IllegalStateException("You must enter a date to loan the book at!");
        }
        this.loanedAt = loanedAt;
        if (toReturnAt.isBefore(loanedAt)) {
            throw new IllegalStateException("Book cannot be returned before it is loaned!");
        }
        this.toReturnAt = toReturnAt;
        this.returnedAt = returnedAt;
    }

    protected Loan() {

    }

    public boolean isLate() {
        return toReturnAt.isAfter(LocalDateTime.now());
    }

    public void returnBook() {
        returnBook(LocalDateTime.now());
    }

    public void returnBook(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    public Book getBook() {
        return book;
    }

    public Loaner getLoaner() {
        return loaner;
    }

    public LocalDateTime getLoanedAt() {
        return loanedAt;
    }

    public LocalDateTime getToReturnAt() {
        return toReturnAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }
}
