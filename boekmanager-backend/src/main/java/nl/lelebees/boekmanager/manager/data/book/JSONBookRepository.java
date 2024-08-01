package nl.lelebees.boekmanager.manager.data.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.lelebees.boekmanager.manager.data.JSONRepository;
import nl.lelebees.boekmanager.manager.domain.book.Book;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JSONBookRepository extends JSONRepository<Book, UUID> implements BookRepository {

    private final ObjectMapper mapper;
    private final Path path = Paths.get("./Book.json");

    public JSONBookRepository() {
        super();
        this.mapper = new ObjectMapper();
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                Files.writeString(path, "[]");
            } catch (IOException e) {
                throw new RuntimeException("Could not create File for saving Books: " + e.getMessage(), e);
            }
        }
        List<Book> books;
        try {
            books = mapper.readValue(path.toUri().toURL(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong parsing JSON!");
            books = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Could not read data", e);
        }
        super.allTypes.addAll(books);
    }

    @Override
    public Book save(Book entity) {
        super.save(entity);
        try {
            mapper.writeValue(new File(path.toUri()), super.allTypes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not save book. Something went wrong parsing to JSON.", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not save book. Could not write to file.", e);
        }
        return entity;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(super.allTypes);
    }
}
