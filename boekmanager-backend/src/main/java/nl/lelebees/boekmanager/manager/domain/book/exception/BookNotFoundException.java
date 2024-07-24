package nl.lelebees.boekmanager.manager.domain.book.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
