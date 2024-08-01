package nl.lelebees.boekmanager.manager.domain.book;

import nl.lelebees.boekmanager.manager.domain.book.exception.NoTitleEnteredException;
import nl.lelebees.boekmanager.manager.domain.name.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest {

    @Test
    @DisplayName("Not entering Title throws exception")
    public void noTitleThrow() {
        assertThrows(NoTitleEnteredException.class, () -> new Book("", new Name("", "", ""), null, ""));
    }

    @Test
    @DisplayName("Correctly entering values creates a book")
    public void canMakeBook() throws NoTitleEnteredException {
        String ISBN = "123456789X123";
        Book book = new Book(ISBN, new Name("Jan", "van de", "Middenweg"), "Blue Danube", "Pizzabal");
        assertEquals(book.getISBN(), ISBN);
    }

}