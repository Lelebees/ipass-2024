package nl.lelebees.boekmanager.manager.data.loan.dao;

import nl.lelebees.boekmanager.manager.domain.loan.Loan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LoanDAO(UUID id, UUID book, UUID loaner, LocalDateTime loanedAt, LocalDateTime toReturnAt,
                      LocalDateTime returnedAt) {

    public static LoanDAO from(Loan loan) {
        return new LoanDAO(loan.getId(), loan.getBook().getId(), loan.getLoaner().getId(), loan.getLoanedAt(), loan.getToReturnAt(), loan.getReturnedAt());
    }

    public static List<LoanDAO> from(List<Loan> loans) {
        List<LoanDAO> result = new ArrayList<>();
        for (Loan loan : loans) {
            result.add(LoanDAO.from(loan));
        }
        return result;
    }
}
