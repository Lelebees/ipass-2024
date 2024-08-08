package nl.lelebees.boekmanager.manager.application;

import nl.lelebees.boekmanager.manager.api.loaner.dto.CreateLoanerDTO;
import nl.lelebees.boekmanager.manager.api.loaner.dto.LoanerDTO;
import nl.lelebees.boekmanager.manager.data.loaner.JSONLoanerRepository;
import nl.lelebees.boekmanager.manager.data.loaner.LoanerRepository;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;
import nl.lelebees.boekmanager.manager.domain.loaner.exception.LoanerNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LoanerService {
    private final LoanerRepository repository;

    public LoanerService(LoanerRepository repository) {
        this.repository = repository;
    }

    public LoanerService() {
        this(new JSONLoanerRepository());
    }

    public LoanerDTO getLoaner(UUID id) throws LoanerNotFoundException {
        return LoanerDTO.from(findLoaner(id));
    }

    public LoanerDTO createLoaner(CreateLoanerDTO dto) {
        return LoanerDTO.from(repository.save(new Loaner(dto.name(), dto.address(), dto.email(), dto.phoneNumber(), dto.notes())));
    }

    public List<LoanerDTO> getAllLoaners() {
        return LoanerDTO.from(repository.getAllLoaners());
    }

    public Loaner findLoaner(UUID id) throws LoanerNotFoundException {
        Optional<Loaner> loanerOptional = repository.findById(id);
        if (loanerOptional.isEmpty()) {
            throw new LoanerNotFoundException("Could not find Loaner with id: " + id);
        }
        return loanerOptional.get();
    }

    public List<LoanerDTO> getLoanersByName(String name){
        return LoanerDTO.from(repository.getLoanersByName(name));
    }
}
