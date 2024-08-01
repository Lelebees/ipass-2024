package nl.lelebees.boekmanager.manager.api.book.dto;

import nl.lelebees.boekmanager.manager.domain.name.Name;

public record CreateBookDTO(String ISBN, Name author, String title, String notes) {
}
