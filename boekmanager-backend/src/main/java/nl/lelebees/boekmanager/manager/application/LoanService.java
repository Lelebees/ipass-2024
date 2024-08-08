package nl.lelebees.boekmanager.manager.application;

import nl.lelebees.boekmanager.manager.api.loan.dto.CreateLoanDTO;
import nl.lelebees.boekmanager.manager.api.loan.dto.LoanDTO;
import nl.lelebees.boekmanager.manager.data.loan.JSONLoanRepository;
import nl.lelebees.boekmanager.manager.data.loan.LoanRepository;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookAlreadyLoanedException;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;
import nl.lelebees.boekmanager.manager.domain.loan.Loan;
import nl.lelebees.boekmanager.manager.domain.loan.exception.LoanNotFoundException;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;
import nl.lelebees.boekmanager.manager.domain.loaner.exception.LoanerNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LoanService {
    private final LoanRepository repository;
    private final LoanerService loanerService;
    private final BookService bookService;

    public LoanService(LoanRepository repository, LoanerService loanerService, BookService bookService) {
        this.repository = repository;
        this.loanerService = loanerService;
        this.bookService = bookService;
    }

    public LoanService() {
        this(new JSONLoanRepository(), new LoanerService(), new BookService());
    }

    public LoanDTO getLoan(UUID id) throws LoanNotFoundException {
        Loan loan = findLoan(id);
        return LoanDTO.from(loan);
    }

    private Loan findLoan(UUID id) throws LoanNotFoundException {
        Optional<Loan> loanOptional = repository.findById(id);
        if (loanOptional.isEmpty()) {
            throw new LoanNotFoundException("Could not find Loan with id:" + id);
        }
        return loanOptional.get();
    }

    public List<LoanDTO> getAllLoans() {
        return LoanDTO.from(repository.getAllLoans());
    }

    public List<LoanDTO> getLoansByLoaner(UUID loanerId) throws LoanerNotFoundException {
        loanerService.getLoaner(loanerId);
        return LoanDTO.from(repository.getLoansByLoaner(loanerId));
    }

    public LoanDTO getLoanByBook(UUID bookId) throws BookNotFoundException, LoanNotFoundException {
        // Ensure book not found is thrown before Loan not found. If the book doesn't exist,
        // that is a much more critical error then if the book isn't Loaned.
        bookService.getBook(bookId);
        Optional<Loan> loanOptional = repository.getLoanByBook(bookId);
        if (loanOptional.isEmpty()) {
            throw new LoanNotFoundException("Book with id:" + bookId + " is not loaned");
        }
        Loan loan = loanOptional.get();
        return LoanDTO.from(loan);
    }

    public LoanDTO createLoan(CreateLoanDTO dto) throws BookNotFoundException, LoanerNotFoundException, BookAlreadyLoanedException {
        Book book = bookService.findBook(dto.bookId());
        Loaner loaner = loanerService.findLoaner(dto.loanerId());
        Optional<Loan> loanOptional = repository.getLoanByBook(dto.bookId());
        if (loanOptional.isPresent()) {
            throw new BookAlreadyLoanedException("Book with id:" + dto.bookId() + " is already loaned to Loaner with id:" + loanOptional.get().getLoaner().getId() + " in Loan with id:" + loanOptional.get().getId());
        }
        return LoanDTO.from(repository.save(new Loan(book, loaner, dto.loanedAt().isEmpty() ? null : LocalDate.parse(dto.loanedAt()), dto.toReturnAt().isEmpty() ? null : LocalDate.parse(dto.toReturnAt()), null)));
    }

    public void deleteLoan(UUID loanId) {
        repository.delete(loanId);
    }
}
