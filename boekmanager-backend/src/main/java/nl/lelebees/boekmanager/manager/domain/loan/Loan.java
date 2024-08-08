package nl.lelebees.boekmanager.manager.domain.loan;

import nl.lelebees.boekmanager.manager.domain.Entity;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.time.LocalDate;
import java.util.UUID;

public class Loan extends Entity<UUID> {
    private Book book;
    private Loaner loaner;
    private LocalDate loanedAt;
    private LocalDate toReturnAt;
    private LocalDate returnedAt;

    public Loan(Book book, Loaner loaner, LocalDate loanedAt, LocalDate toReturnAt, LocalDate returnedAt) {
        this(UUID.randomUUID(), book, loaner, loanedAt, toReturnAt, returnedAt);
    }

    public Loan(UUID id, Book book, Loaner loaner, LocalDate loanedAt, LocalDate toReturnAt, LocalDate returnedAt) {
        super(id);
        this.book = book;
        this.loaner = loaner;
        this.loanedAt = loanedAt;
        if (loanedAt == null) {
            this.loanedAt = LocalDate.now();
        }
        if (toReturnAt != null && toReturnAt.isBefore(loanedAt)) {
            throw new IllegalStateException("Book cannot be returned before it is loaned!");
        }
        this.toReturnAt = toReturnAt;
        this.returnedAt = returnedAt;
    }

    protected Loan() {

    }

    public boolean isLate() {
        if (toReturnAt == null) {
            return false;
        }
        return toReturnAt.isAfter(LocalDate.now());
    }

    public void returnBook() {
        returnBook(LocalDate.now());
    }

    public void returnBook(LocalDate returnedAt) {
        this.returnedAt = returnedAt;
    }

    public Book getBook() {
        return book;
    }

    public Loaner getLoaner() {
        return loaner;
    }

    public LocalDate getLoanedAt() {
        return loanedAt;
    }

    public LocalDate getToReturnAt() {
        return toReturnAt;
    }

    public LocalDate getReturnedAt() {
        return returnedAt;
    }
}
