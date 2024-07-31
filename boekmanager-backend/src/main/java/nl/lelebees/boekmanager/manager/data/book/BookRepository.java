package nl.lelebees.boekmanager.manager.data.book;

import nl.lelebees.boekmanager.manager.data.Repository;
import nl.lelebees.boekmanager.manager.domain.book.Book;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends Repository<Book, UUID> {
    List<Book> getAllBooks();
}
