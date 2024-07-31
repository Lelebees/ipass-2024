package nl.lelebees.boekmanager.manager.data.loaner;

import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CSVLoanerRepository implements LoanerRepository {

    List<Loaner> allLoaners;

    @Override
    public Optional<Loaner> findById(UUID uuid) {
        for (Loaner loaner : allLoaners) {
            if (loaner.getId().equals(uuid)) {
                return Optional.of(loaner);
            }
        }
        return Optional.empty();
    }

    @Override
    public Loaner save(Loaner entity) {
        allLoaners.add(entity);
        return entity;
    }

    @Override
    public void delete(UUID uuid) {
        allLoaners.removeIf(loaner -> loaner.getId().equals(uuid));
    }

    @Override
    public List<Loaner> getAllLoaners() {
        return new ArrayList<>(allLoaners);
    }
}
