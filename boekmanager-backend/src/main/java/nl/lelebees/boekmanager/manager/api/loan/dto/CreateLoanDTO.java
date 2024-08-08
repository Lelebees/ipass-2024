package nl.lelebees.boekmanager.manager.api.loan.dto;

import java.util.UUID;

public record CreateLoanDTO(UUID bookId, UUID loanerId, String loanedAt, String toReturnAt) {
}
