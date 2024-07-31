package nl.lelebees.boekmanager.manager.data;

import java.util.Optional;

public interface Repository<Type, ID> {
    Optional<Type> findById(ID id);

    Type save(Type entity);

    void delete(ID id);
}
