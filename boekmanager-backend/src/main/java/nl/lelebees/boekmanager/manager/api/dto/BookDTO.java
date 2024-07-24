package nl.lelebees.boekmanager.manager.api.dto;

import nl.lelebees.boekmanager.manager.domain.Name;
import nl.lelebees.boekmanager.manager.domain.book.Book;

import java.util.UUID;

public record BookDTO(UUID id, String ISBN, Name author, String title, String notes, boolean isLoaned) {

    public static BookDTO from(Book book) {
        return new BookDTO(book.getId(), book.getISBN(), book.getAuthor(), book.getTitle(), book.getNotes(), false);
    }
}
