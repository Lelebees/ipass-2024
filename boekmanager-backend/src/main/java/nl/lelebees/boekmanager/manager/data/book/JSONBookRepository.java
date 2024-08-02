package nl.lelebees.boekmanager.manager.data.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import nl.lelebees.boekmanager.manager.data.JSONRepository;
import nl.lelebees.boekmanager.manager.domain.book.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
