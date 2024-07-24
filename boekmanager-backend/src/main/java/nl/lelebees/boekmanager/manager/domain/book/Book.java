package nl.lelebees.boekmanager.manager.domain.book;

import nl.lelebees.boekmanager.manager.domain.Name;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Book {
    private UUID id;
    private String ISBN;
    private Name author;
    private String title;
    private String notes;

    private static final List<Book> allBooks = new ArrayList<>();

    protected Book(UUID id, String ISBN, Name author, String title, String notes) {
        this.id = id;
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.notes = notes;
        allBooks.add(this);
    }

    public Book(String ISBN, Name author, String title, String notes) {
        this(UUID.randomUUID(), ISBN, author, title, notes);
    }

    public UUID getId() {
        return id;
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

    public boolean isLoaned() {
        throw new RuntimeException("Method not implemented");
    }

    public boolean isReserved() {
        throw new RuntimeException("Method not implemented");
    }

    public Loaner getLoaner() {
        throw new RuntimeException("Method not implemented");
    }

    public Loaner getFirstReserver() {
        throw new RuntimeException("Method not implemented");
    }

    public static List<Book> getAllBooks() {
        return Book.allBooks;
    }
}
