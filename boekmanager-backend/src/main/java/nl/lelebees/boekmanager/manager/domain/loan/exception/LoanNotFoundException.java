package nl.lelebees.boekmanager.manager.domain.loan.exception;

public class LoanNotFoundException extends Exception {
    public LoanNotFoundException(String message) {
        super(message);
    }
}
