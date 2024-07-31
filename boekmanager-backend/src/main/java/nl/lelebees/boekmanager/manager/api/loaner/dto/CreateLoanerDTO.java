package nl.lelebees.boekmanager.manager.api.loaner.dto;

import nl.lelebees.boekmanager.manager.domain.loaner.Address;
import nl.lelebees.boekmanager.manager.domain.name.Name;

public record CreateLoanerDTO(Name name, Address address, String email, String phoneNumber, String notes) {
}
