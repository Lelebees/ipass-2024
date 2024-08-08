```mermaid
classDiagram 
    direction LR
    class Book {
        -UUID id [get]
        -String ISBN [get]
        -Name author [get]
        -String title [get]
        -String notes [get]
        +isLoaned() bool
%%        +isReserved() bool
        +getLoaner() Loaner
%%        +getFirstReserver() Loaner
    }
    Book "1" --o "0..1" Loan
%%    ISBN "1" --* "1..*" Book
%%    class ISBN {
%%        <<abstract>>
%%        -char[] digits
%%        +fromString(String s)$ ISBN
%%        +isValid()* boolean
%%        +getRegistrationGroup()* char[]
%%        +getRegistrant()* char[]
%%        +getPublication()* char[]
%%        +getCheckDigit()* char
%%        +toString()* String
%%    }
%%    
%%    class ISBN10 {
%%        -char[10] digits
%%        +fromISBN13()$ ISBN10
%%    }
%%    ISBN10 --|> ISBN
%%    class ISBN13 {
%%        -char[13] digits
%%        +fromISBN10()$ ISBN13
%%        +getPrefixElement() char[3]
%%    }
%%    ISBN13 --|> ISBN
    Loan "0..*" o-- "1" Loaner
    class Loan {
        -UUID id [get]
        -Book loanedBook [get]
        -Loaner loaner [get]
        -LocalDateTime loanedAt [get]
        -LocalDateTime toReturnAt [get]
        -LocalDateTime returnedAt [get]
        +isLate() bool
        +returnBook()
        +returnBook(LocalDateTime returnedAt)
    }
%%    Book "1" --o "0..*" Reservation
%%    Reservation "0..*" o-- "1" Loaner
%%    class Reservation {
%%        -UUID id [get]
%%        -Book reservedBook [get]
%%        -Loaner reserver [get]
%%        -LocalDateTime reservedAt [get]
%%    }
    class Loaner {
        -UUID id [get]
        -Name name [get]
        -Adress address [get]
        -String email [get]
        -String phoneNumber [get]
        -String notes [get]
%%        +getLoans() List~Loan~
%%        +getReservations() List~Reservation~
    }
    Loaner "1..*" *-- "1" Address
    class Address {
        -String houseNumber [get]
        -String streetName [get]
        -String townName [get]
        -String country [get]
        -String postalCode [get]
        +toString() String
    }
%%    class Email {
%%        -String email
%%        +toString() String
%%    }
%%    Loaner "1..*" *-- "1" Email
%%    class PhoneNumber {
%%        -int countryCode
%%        -int number
%%        +toString() String
%%        +getLocalNumber() String
%%    }
%%    Loaner "1..*" *-- "1" PhoneNumber
%%    Address "1" *-- "1" HouseNumber
%%    class HouseNumber {
%%        -Integer number [get]
%%        -String addition [get]
%%        +getNumber() Integer
%%        +getAddition() String
%%        +toString() String
%%    }
%%    Address "0..*" *-- "1" PostalCode
%%    class PostalCode {
%%        -char[6] code
%%        +getRegio() char[2]
%%        +getWijk() char[2]
%%        +toString() String
%%    }
    class Name {
        -String firstName [get]
        -String middleName [get]
        -String lastName [get]
        +toString() String
        +toString(NameFormat format) String
    }
    class NameFormat {
        <<enumeration>>
        FIRST_MIDDLE_LAST
        LAST_FIRST_MIDDLE
    }
    Name ..> NameFormat
    Book "0..*" *-- "1" Name : author
    Loaner "0..1" *-- "1" Name
```
Voor de leesbaarheid zijn getters en setters achterwege gelaten. 
In plaats daarvan zijn deze zichtbaar gemaakt met `[get, set]`, `[get]` of `[set]` achter de relevante variabele.

Tevens zijn, om herhaling te voorkomen, 
de implementatiemethoden die gedefinieërd zijn in Interface of Abstracte klassen achterwege gelaten in de relevante implementatieklassen. 
Tenzij deze, in het geval van abstracte klassen, een non-abstracte methode overschrijven.