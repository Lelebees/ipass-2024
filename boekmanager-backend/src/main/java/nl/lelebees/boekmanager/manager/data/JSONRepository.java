package nl.lelebees.boekmanager.manager.data;

import nl.lelebees.boekmanager.manager.domain.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JSONRepository<Type extends Entity<ID>, ID> implements Repository<Type, ID> {

    protected final List<Type> allTypes;

    public JSONRepository(List<Type> allTypes) {
        this.allTypes = allTypes;
    }

    public JSONRepository() {
        this(new ArrayList<>());
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
        return entity;
    }

    @Override
    public void delete(ID id) {
        allTypes.removeIf(type -> type.getId().equals(id));
    }
}
