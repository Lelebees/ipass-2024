package nl.lelebees.boekmanager.manager.data.loan;

import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;
import nl.lelebees.boekmanager.manager.domain.loan.Loan;
import nl.lelebees.boekmanager.manager.domain.loaner.Address;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;
import nl.lelebees.boekmanager.manager.domain.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONLoanRepositoryTest {

    @Test
    @DisplayName("Can Save")
    public void canSave() throws NoTitleEnteredException {
        JSONLoanRepository repository = new JSONLoanRepository();
        Loan loan = repository.save(
                new Loan(
                        new Book("123456789X123", new Name("A", "van der", "A"), "Titel", "notities"),
                        new Loaner(new Name("A", "van der", "A"), new Address("1", "straat", "dorp", "land", "1234AB"), "a@a.com", "1234567890", "Notitie"),
                        LocalDateTime.now().minusDays(3L), LocalDateTime.now(), null)
        );
        assertEquals("123456789X123", loan.getBook().getISBN());
    }
}