package nl.lelebees.boekmanager.manager.application;

import nl.lelebees.boekmanager.manager.api.book.dto.BookDTO;
import nl.lelebees.boekmanager.manager.api.book.dto.CreateBookDTO;
import nl.lelebees.boekmanager.manager.data.book.BookRepository;
import nl.lelebees.boekmanager.manager.data.book.CSVBookRepository;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.book.exception.BookNotFoundException;
import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookService() {
        this(new CSVBookRepository());
    }

    public BookDTO getBook(UUID bookId) throws BookNotFoundException {
        return BookDTO.from(findBook(bookId));
    }

    public BookDTO createBook(CreateBookDTO bookDTO) throws NoTitleEnteredException {
        return BookDTO.from(repository.save(new Book(bookDTO.ISBN(), bookDTO.author(), bookDTO.title(), bookDTO.notes())));
    }

    public List<BookDTO> getAllBooks() {
        return BookDTO.listFrom(repository.getAllBooks());
    }

    private Book findBook(UUID bookId) throws BookNotFoundException {
        Optional<Book> bookOptional = repository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException("Could not find Book with id: " + bookId);
        }
        return bookOptional.get();
    }
}
