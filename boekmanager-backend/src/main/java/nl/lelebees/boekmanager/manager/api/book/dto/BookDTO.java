package nl.lelebees.boekmanager.manager.api.book.dto;

import nl.lelebees.boekmanager.manager.domain.name.Name;
import nl.lelebees.boekmanager.manager.domain.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record BookDTO(UUID id, String ISBN, Name author, String title, String notes, boolean isLoaned) {

    public static BookDTO from(Book book) {
        return new BookDTO(book.getId(), book.getISBN(), book.getAuthor(), book.getTitle(), book.getNotes(), false);
    }

    public static List<BookDTO> from(List<Book> allBooks) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : allBooks) {
            bookDTOList.add(BookDTO.from(book));
        }
        return bookDTOList;
    }
}
