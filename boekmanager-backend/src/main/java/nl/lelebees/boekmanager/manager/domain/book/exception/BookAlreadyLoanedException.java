package nl.lelebees.boekmanager.manager.domain.book.exception;

public class BookAlreadyLoanedException extends Exception {
    public BookAlreadyLoanedException(String message) {
        super(message);
    }
}
