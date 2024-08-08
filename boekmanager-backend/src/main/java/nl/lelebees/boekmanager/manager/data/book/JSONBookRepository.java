package nl.lelebees.boekmanager.manager.data.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import nl.lelebees.boekmanager.manager.data.JSONRepository;
import nl.lelebees.boekmanager.manager.domain.book.Book;
import nl.lelebees.boekmanager.manager.domain.name.Name;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static nl.lelebees.boekmanager.manager.domain.name.NameFormat.FIRST_MIDDLE_LAST;
import static nl.lelebees.boekmanager.manager.domain.name.NameFormat.LAST_FIRST_MIDDLE;

public class JSONBookRepository extends JSONRepository<Book, UUID> implements BookRepository {

    public JSONBookRepository() {
        super(Book.class);
        List<Book> types;
        try {
            String content = Files.readString(path);
            types = mapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong parsing JSON for " + clazz.getSimpleName() + "!");
            System.out.println(e);
            types = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong writing to file.", e);
        }
        allTypes.addAll(types);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(allTypes);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        String query = author.trim().toLowerCase();
        return allTypes.stream().filter(book -> {
            Name bookAuthor = book.getAuthor();
            String authorFML = bookAuthor.toString(FIRST_MIDDLE_LAST).trim().toLowerCase();
            String authorLFM = bookAuthor.toString(LAST_FIRST_MIDDLE).trim().toLowerCase();
            return authorFML.contains(query) || authorLFM.contains(query);
        }).toList();
    }
}
