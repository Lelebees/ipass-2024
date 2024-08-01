package nl.lelebees.boekmanager.manager.domain;

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
}
