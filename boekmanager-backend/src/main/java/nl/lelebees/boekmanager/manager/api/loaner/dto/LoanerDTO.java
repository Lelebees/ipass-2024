package nl.lelebees.boekmanager.manager.api.loaner.dto;

import nl.lelebees.boekmanager.manager.domain.loaner.Address;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;
import nl.lelebees.boekmanager.manager.domain.name.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LoanerDTO(UUID id, Name name, Address address, String email, String phoneNumber, String notes) {
    public static LoanerDTO from(Loaner loaner) {
        return new LoanerDTO(loaner.getId(), loaner.getName(), loaner.getAddress(), loaner.getEmail(), loaner.getPhoneNumber(), loaner.getNotes());
    }

    public static List<LoanerDTO> from(List<Loaner> loaners) {
        List<LoanerDTO> result = new ArrayList<>();
        for (Loaner loaner : loaners) {
            result.add(LoanerDTO.from(loaner));
        }
        return result;
    }
}
