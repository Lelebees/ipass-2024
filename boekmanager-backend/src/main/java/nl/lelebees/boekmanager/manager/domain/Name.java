package nl.lelebees.boekmanager.manager.domain;

import static nl.lelebees.boekmanager.manager.domain.NameOrder.FIRST_MIDDLE_LAST;

public record Name(String firstName, String middleName, String lastName) {

    @Override
    public String toString() {
        return this.toString(FIRST_MIDDLE_LAST);
    }

    public String toString(NameOrder order) {
        return switch (order) {
            case FIRST_MIDDLE_LAST -> firstName + " " + middleName + " " + lastName;
            case LAST_FIRST_MIDDLE -> lastName + ", " + firstName + " " + middleName;
        };
    }
}
