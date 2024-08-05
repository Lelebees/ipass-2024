package nl.lelebees.boekmanager.manager.api.loan.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateLoanDTO(UUID bookId, UUID loanerId, LocalDateTime loanedAt, LocalDateTime toReturnAt) {
}
