package nl.lelebees.boekmanager.manager.data.loaner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.lelebees.boekmanager.manager.data.JSONRepository;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JSONLoanerRepository extends JSONRepository<Loaner, UUID> implements LoanerRepository {

    private final ObjectMapper mapper;
    private final String url = "Loaner.json";
    private final Path path = Paths.get(url);

    public JSONLoanerRepository() {
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
        List<Loaner> loaners;
        try {
            String content = Files.readString(path);
            loaners = mapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong parsing JSON!");
            System.out.println(e);
            loaners = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.allTypes.addAll(loaners);
    }

    @Override
    public Loaner save(Loaner entity) {
        super.save(entity);
        try {
            String text = mapper.writeValueAsString(super.allTypes);
            Files.writeString(path, text);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not save book. Something went wrong parsing to JSON.", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not save book. Could not write to file.", e);
        }
        return entity;
    }

    @Override
    public List<Loaner> getAllLoaners() {
        return new ArrayList<>(super.allTypes);
    }
}
