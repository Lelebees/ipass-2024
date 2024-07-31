package nl.lelebees.boekmanager.manager.api.dto;

import nl.lelebees.boekmanager.manager.domain.name.Name;

public record CreateBookDTO(String ISBN, Name author, String title, String notes) {
}
