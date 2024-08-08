package nl.lelebees.boekmanager.manager.data.loan;

import nl.lelebees.boekmanager.manager.data.Repository;
import nl.lelebees.boekmanager.manager.domain.loan.Loan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends Repository<Loan, UUID> {
    List<Loan> getAllLoans();

    List<Loan> getLoansByLoaner(UUID loanerId);
    Optional<Loan> getLoanByBook(UUID bookId);
}
