package nl.lelebees.boekmanager.manager.api.loan.dto;

import nl.lelebees.boekmanager.manager.api.book.dto.BookDTO;
import nl.lelebees.boekmanager.manager.api.loaner.dto.LoanerDTO;
import nl.lelebees.boekmanager.manager.domain.loan.Loan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LoanDTO(UUID id, BookDTO book, LoanerDTO loaner, LocalDateTime loanedAt, LocalDateTime toReturnAt, LocalDateTime returnedAt) {
    public static LoanDTO from(Loan loan) {
        return new LoanDTO(loan.getId(), BookDTO.from(loan.getBook()), LoanerDTO.from(loan.getLoaner()), loan.getLoanedAt(), loan.getToReturnAt(), loan.getReturnedAt());
    }

    public static List<LoanDTO> from(List<Loan> loans) {
        List<LoanDTO> loanDTOS = new ArrayList<>();
        for (Loan loan : loans) {
            loanDTOS.add(LoanDTO.from(loan));
        }
        return loanDTOS;
    }
}
