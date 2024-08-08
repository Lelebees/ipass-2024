package nl.lelebees.boekmanager.manager.data;

import java.util.Optional;

public interface Repository<Entity, ID> {
    Optional<Entity> findById(ID id);

    Entity save(Entity entity);

    void delete(ID id);
}
