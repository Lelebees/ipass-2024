package nl.lelebees.boekmanager.manager.domain.loaner;

import nl.lelebees.boekmanager.manager.domain.Entity;
import nl.lelebees.boekmanager.manager.domain.loan.Loan;
import nl.lelebees.boekmanager.manager.domain.name.Name;

import java.util.List;
import java.util.UUID;

public class Loaner extends Entity<UUID> {
    private Name name;
    private Address address;
    private String email;
    private String phoneNumber;
    private String notes;


    private Loaner(UUID id, Name name, Address address, String email, String phoneNumber, String notes) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Loaner(Name name, Address address, String email, String phoneNumber, String notes) {
        this(UUID.randomUUID(), name, address, email, phoneNumber, notes);
    }

    public List<Loan> getLoans() {
        throw new RuntimeException("Not implemented");
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }
}
