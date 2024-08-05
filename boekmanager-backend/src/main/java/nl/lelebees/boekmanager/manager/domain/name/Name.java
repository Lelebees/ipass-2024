package nl.lelebees.boekmanager.manager.domain.name;

import static nl.lelebees.boekmanager.manager.domain.name.NameFormat.FIRST_MIDDLE_LAST;

public record Name(String firstName, String middleName, String lastName) {

    @Override
    public String toString() {
        return this.toString(FIRST_MIDDLE_LAST);
    }

    public String toString(NameFormat format) {
        if (middleName.isEmpty()) {
            return switch (format) {
                case FIRST_MIDDLE_LAST -> firstName + " " + lastName;
                case LAST_FIRST_MIDDLE -> lastName + ", " + firstName;
            };
        }
        return switch (format) {
            case FIRST_MIDDLE_LAST -> firstName + " " + middleName + " " + lastName;
            case LAST_FIRST_MIDDLE -> lastName + ", " + firstName + " " + middleName;
        };
    }
}
