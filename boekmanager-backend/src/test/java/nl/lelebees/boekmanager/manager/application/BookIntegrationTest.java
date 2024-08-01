package nl.lelebees.boekmanager.manager.application;

import nl.lelebees.boekmanager.manager.api.book.dto.BookDTO;
import nl.lelebees.boekmanager.manager.api.book.dto.CreateBookDTO;
import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;
import nl.lelebees.boekmanager.manager.domain.name.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookIntegrationTest {

    @Test
    public void canSave() throws NoTitleEnteredException {
        BookService bookService = new BookService();
        BookDTO book = bookService.createBook(new CreateBookDTO("123456789X123", new Name("", "", ""), "", ""));
        assertEquals(book.ISBN(),"123456789X123");
    }

}