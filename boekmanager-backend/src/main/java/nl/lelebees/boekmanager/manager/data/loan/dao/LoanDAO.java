package nl.lelebees.boekmanager.manager.data.loan.dao;

import nl.lelebees.boekmanager.manager.domain.loan.Loan;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LoanDAO(UUID id, UUID book, UUID loaner, String loanedAt, String toReturnAt,
                      String returnedAt) {

    public static LoanDAO from(Loan loan) {
        return new LoanDAO(loan.getId(), loan.getBook().getId(), loan.getLoaner().getId(), loan.getLoanedAt() == null ? null : loan.getLoanedAt().format(DateTimeFormatter.ISO_LOCAL_DATE), loan.getToReturnAt() == null ? null : loan.getToReturnAt().format(DateTimeFormatter.ISO_LOCAL_DATE), loan.getReturnedAt() == null ? null : loan.getReturnedAt().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public static List<LoanDAO> from(List<Loan> loans) {
        List<LoanDAO> result = new ArrayList<>();
        for (Loan loan : loans) {
            result.add(LoanDAO.from(loan));
        }
        return result;
    }
}
