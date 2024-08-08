package nl.lelebees.boekmanager.manager.domain.loaner;

public record Address(String houseNumber, String streetName, String townName, String country, String postalCode) {
    @Override
    public String toString() {
        return streetName + " " + houseNumber + ", " + postalCode + " " + townName + ", " + country;
    }
}
