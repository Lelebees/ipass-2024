package nl.lelebees.boekmanager.manager.application;

import nl.lelebees.boekmanager.manager.api.dto.BookDTO;
import nl.lelebees.boekmanager.manager.api.dto.CreateBookDTO;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;
import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;

import java.util.List;
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

    public BookDTO createBook(CreateBookDTO bookDTO) throws NoTitleEnteredException {
        return BookDTO.from(new Book(bookDTO.ISBN(), bookDTO.author(), bookDTO.title(), bookDTO.notes()));
    }

    public List<BookDTO> getAllBooks() {
        return BookDTO.listFrom(Book.getAllBooks());
    }
}
