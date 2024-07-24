package nl.lelebees.boekmanager.manager.application;

import nl.lelebees.boekmanager.manager.api.dto.BookDTO;
import nl.lelebees.boekmanager.manager.api.dto.CreateBookDTO;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;

import java.util.UUID;

public class BookService {

    public BookDTO getBook(UUID bookId) throws BookNotFoundException {
        return BookDTO.from(findBook(bookId));
    }

    private Book findBook(UUID bookId) throws BookNotFoundException {
        for (Book book : Book.getAllBooks()) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        throw new BookNotFoundException("Could not find Book with id: " + bookId);
    }

    public BookDTO createBook(CreateBookDTO bookDTO) {
        return BookDTO.from(new Book(bookDTO.ISBN(), bookDTO.author(), bookDTO.title(), bookDTO.notes()));
    }
}
