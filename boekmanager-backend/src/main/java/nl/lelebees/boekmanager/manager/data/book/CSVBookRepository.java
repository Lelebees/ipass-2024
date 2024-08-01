package nl.lelebees.boekmanager.manager.data.book;

import nl.lelebees.boekmanager.manager.domain.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CSVBookRepository implements BookRepository{
    private final List<Book> allBooks = new ArrayList<>();
    @Override
    public Optional<Book> findById(UUID uuid) {
        for (Book book : allBooks) {
            if (book.getId().equals(uuid)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public Book save(Book entity) {
        allBooks.add(entity);
        return entity;
    }

    @Override
    public void delete(UUID uuid) {
        allBooks.removeIf(book -> book.getId().equals(uuid));
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(allBooks);
    }
}
