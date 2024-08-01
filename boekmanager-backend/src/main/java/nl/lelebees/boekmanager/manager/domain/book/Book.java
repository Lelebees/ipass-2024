package nl.lelebees.boekmanager.manager.domain.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.lelebees.boekmanager.manager.domain.Entity;
import nl.lelebees.boekmanager.manager.domain.name.Name;
import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book extends Entity<UUID> {
    @JsonProperty("isbn")
    private String ISBN;
    private Name author;
    private String title;
    private String notes;

    protected Book(UUID id, String ISBN, Name author, String title, String notes) throws NoTitleEnteredException {
        super(id);
        this.ISBN = ISBN;
        this.author = author;
        if (title == null) {
            throw new NoTitleEnteredException("Title is null!");
        }
        this.title = title;
        this.notes = notes;
    }

    public Book(String ISBN, Name author, String title, String notes) throws NoTitleEnteredException {
        this(UUID.randomUUID(), ISBN, author, title, notes);
    }

    protected Book(){
//        super();
    }

    public String getISBN() {
        return ISBN;
    }

    public Name getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

//    public boolean isLoaned() {
//        throw new RuntimeException("Method not implemented");
//    }

//    public boolean isReserved() {
//        throw new RuntimeException("Method not implemented");
//    }

//    public Loaner getLoaner() {
//        throw new RuntimeException("Method not implemented");
//    }

//    public Loaner getFirstReserver() {
//        throw new RuntimeException("Method not implemented");
//    }
}
