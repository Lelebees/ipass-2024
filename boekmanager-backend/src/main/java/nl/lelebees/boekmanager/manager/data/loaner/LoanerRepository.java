package nl.lelebees.boekmanager.manager.data.loaner;

import nl.lelebees.boekmanager.manager.data.Repository;
import nl.lelebees.boekmanager.manager.domain.loaner.Loaner;

import java.util.List;
import java.util.UUID;

public interface LoanerRepository extends Repository<Loaner, UUID> {
    List<Loaner> getAllLoaners();

    List<Loaner> getLoanersByName(String name);
}
