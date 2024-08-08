package nl.lelebees.boekmanager.manager.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.lelebees.boekmanager.manager.domain.Entity;
import org.glassfish.jersey.logging.LoggingFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public abstract class JSONRepository<Type extends Entity<ID>, ID> implements Repository<Type, ID> {
    protected final List<Type> allTypes;
    protected final Class<Type> clazz;

    protected final ObjectMapper mapper;
    protected final Path path;

    protected Logger logger = Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME);

    public JSONRepository(List<Type> allTypes, Class<Type> clazz) {
        this.allTypes = allTypes;
        this.clazz = clazz;
        this.path = Paths.get(clazz.getSimpleName() + ".json");
        this.mapper = new ObjectMapper().findAndRegisterModules();
        if (!Files.exists(path)) {
            logger.warning(clazz.getSimpleName() + ".json does not exist!");
            try {
                Files.createFile(path);
                Files.writeString(path, "[]");
            } catch (IOException e) {
                throw new RuntimeException("Could not create File for saving " + clazz.getSimpleName() + "s: " + e.getMessage(), e);
            }
        }
        logger.info("Found " + clazz.getSimpleName() + ".json");
    }

    public JSONRepository(Class<Type> clazz) {
        this(new ArrayList<>(), clazz);
    }

    @Override
    public Optional<Type> findById(ID id) {
        for (Type type : allTypes) {
            if (type.getId().equals(id)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    @Override
    public Type save(Type entity) {
        allTypes.add(entity);
        try {
            String text = mapper.writeValueAsString(allTypes);
            Files.writeString(path, text);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not save " + clazz.getSimpleName() + ". Something went wrong parsing to JSON.", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not save " + clazz.getSimpleName() + ". Could not write to file.", e);
        }
        return entity;
    }

    @Override
    public void delete(ID id) {
        allTypes.removeIf(type -> type.getId().equals(id));
    }
}
