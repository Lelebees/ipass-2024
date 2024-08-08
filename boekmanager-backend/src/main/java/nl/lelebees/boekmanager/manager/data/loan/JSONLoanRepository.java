package nl.lelebees.boekmanager.manager.data.loan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import nl.lelebees.boekmanager.manager.data.JSONRepository;
import nl.lelebees.boekmanager.manager.data.book.JSONBookRepository;
import nl.lelebees.boekmanager.manager.data.loan.dao.LoanDAO;
import nl.lelebees.boekmanager.manager.data.loaner.JSONLoanerRepository;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;
import nl.lelebees.boekmanager.manager.domain.loan.Loan;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;
import nl.lelebees.boekmanager.manager.domain.loaner.exception.LoanerNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JSONLoanRepository extends JSONRepository<Loan, UUID> implements LoanRepository {
    private final JSONBookRepository bookRepository;
    private final JSONLoanerRepository loanerRepository;

    public JSONLoanRepository() {
        super(Loan.class);
        this.bookRepository = new JSONBookRepository();
        this.loanerRepository = new JSONLoanerRepository();
        List<Loan> types;
        try {
            String content = Files.readString(path);
            List<LoanDAO> daos = mapper.readValue(content, new TypeReference<>() {
            });
            types = new ArrayList<>();
            for (LoanDAO dao : daos) {
                Book book = bookRepository.findById(dao.book()).orElseThrow(() -> new BookNotFoundException("Could not find Book with id: " + dao.book()));
                Loaner loaner = loanerRepository.findById(dao.loaner()).orElseThrow(() -> new LoanerNotFoundException("Could not find Loaner with id:" + dao.loaner()));
                types.add(new Loan(dao.id(), book, loaner, dao.loanedAt() == null ? null : LocalDate.parse(dao.loanedAt()), dao.toReturnAt() == null ? null : LocalDate.parse(dao.toReturnAt()), dao.returnedAt() == null ? null : LocalDate.parse(dao.returnedAt())));
            }
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong parsing JSON for " + clazz.getSimpleName() + "!");
            System.out.println(e);
            types = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong writing to file.", e);
        } catch (BookNotFoundException e) {
            throw new IllegalStateException("Could not find Book belonging to a Loan", e);
        } catch (LoanerNotFoundException e) {
            throw new IllegalStateException("Could not find Loaner belonging to a Loan", e);
        }
        allTypes.addAll(types);
    }

    @Override
    protected void persistData() throws IOException {
        List<LoanDAO> daos = new ArrayList<>();
        for (Loan type : allTypes) {
            bookRepository.save(type.getBook());
            loanerRepository.save(type.getLoaner());
            daos.add(LoanDAO.from(type));
        }
        String text = mapper.writeValueAsString(daos);
        Files.writeString(path, text);
    }

    @Override
    public List<Loan> getAllLoans() {
        return new ArrayList<>(allTypes);
    }

    @Override
    public List<Loan> getLoansByLoaner(UUID loanerId) {
        List<Loan> loans = new ArrayList<>();
        for (Loan loan : allTypes) {
            if (loan.getLoaner().getId().equals(loanerId)) {
                loans.add(loan);
            }
        }
        return loans;
    }

    @Override
    public Optional<Loan> getLoanByBook(UUID bookId) {
        return allTypes.stream().filter(loan -> loan.getBook().getId().equals(bookId)).findFirst();
    }
}
