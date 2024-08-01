package nl.lelebees.boekmanager.manager.domain.loan;

import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.time.LocalDateTime;
import java.util.UUID;

public class Loan {
    private final UUID id;
    private final Book book;
    private final Loaner loaner;
    private LocalDateTime loanedAt;
    private LocalDateTime toReturnAt;
    private LocalDateTime returnedAt;

    public Loan(Book book, Loaner loaner, LocalDateTime loanedAt, LocalDateTime toReturnAt, LocalDateTime returnedAt) {
        this(UUID.randomUUID(), book, loaner, loanedAt, toReturnAt, returnedAt);
    }

    protected Loan(UUID id, Book book, Loaner loaner, LocalDateTime loanedAt, LocalDateTime toReturnAt, LocalDateTime returnedAt) {
        this.id = id;
        this.book = book;
        this.loaner = loaner;
        this.loanedAt = loanedAt;
        this.toReturnAt = toReturnAt;
        this.returnedAt = returnedAt;
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

    public UUID getId() {
        return id;
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
