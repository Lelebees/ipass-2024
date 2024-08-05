package nl.lelebees.boekmanager.manager.data.loaner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import nl.lelebees.boekmanager.manager.data.JSONRepository;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;
import nl.lelebees.boekmanager.manager.domain.name.Name;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static nl.lelebees.boekmanager.manager.domain.name.NameFormat.FIRST_MIDDLE_LAST;
import static nl.lelebees.boekmanager.manager.domain.name.NameFormat.LAST_FIRST_MIDDLE;

public class JSONLoanerRepository extends JSONRepository<Loaner, UUID> implements LoanerRepository {

    public JSONLoanerRepository() {
        super(Loaner.class);
        List<Loaner> types;
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
    public List<Loaner> getAllLoaners() {
        return new ArrayList<>(allTypes);
    }

    @Override
    public List<Loaner> getLoanersByName(String name){
        String query = name.trim().toLowerCase();
        return allTypes.stream().filter(loaner -> {
            Name loanerName = loaner.getName();
            String nameFML = loanerName.toString(FIRST_MIDDLE_LAST).trim().toLowerCase();
            String nameLMF = loanerName.toString(LAST_FIRST_MIDDLE).trim().toLowerCase();
            return nameFML.contains(query) || nameLMF.contains(query);
        }).toList();
    }
}
