package nl.lelebees.boekmanager.manager.domain;

import java.util.Objects;

public abstract class Entity<ID> {
    private ID id;

    protected Entity() {
        // Jackson?
    }

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity<?> entity)) return false;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
