```mermaid
classDiagram 
    direction TB
    class Book {
        -UUID id [get]
        -ISBN ISBN [get]
        -Name author [get]
        -String title [get]
        -String notes [get]
        +isLoaned() bool
    }
    Book "1" --o "1" Loan
    ISBN "1" <-- "1..*" Book
    class ISBN {
        <<abstract>>
        -char[] digits
        +fromString(String s)$ ISBN
        +isValid()* boolean
        +getRegistrationGroup()* char[]
        +getRegistrant()* char[]
        +getPublication()* char[]
        +getCheckDigit()* char
        +toString()* String
    }
    
    class ISBN10 {
        -char[10] digits
        +fromISBN13()$ ISBN10
    }
    ISBN10 --|> ISBN
    class ISBN13 {
        -char[13] digits
        +fromISBN10()$ ISBN13
        +getPrefixElement() char[3]
    }
    ISBN13 --|> ISBN
    Loan "0..*" -- "1" Loaner
    class Loan {
        -Book loanedBook [get]
        -LocalDateTime loanedAt [get]
        -LocalDateTime toReturnAt [get]
        -LocalDateTime wasReturnedAt [get]
        +isLate() bool
    }
    Book "1" --o "1" Reservation
    Reservation "0..*" -- "1" Loaner
    class Reservation {
        -Book reservedBook [get]
        -LocalDateTime reservedAt [get]
    }
    class Loaner {
        -UUID id [get]
        -Name naam [get]
        -Adress adres [get]
        -String notities [get]
        -List~Loan~ leningen [get]
        -List~Reservation~ reserveringen [get]
    }
    Loaner "1..*" *-- "1" Adress
    class Adress {
        -HouseNumber huisnummer [get]
        -String straatNaam [get]
        -String plaatsNaam [get]
        -String provincie [get]
        -PostalCode postcode [get]
    }
    Adress "1" -- "1" HouseNumber
    class HouseNumber {
        -Integer nummer [get]
        -String toevoeging [get]
        +getNummer() Integer
        +getToevoeging() String
        +toString() String
    }
    Adress "0..*" -- "1" PostalCode
    class PostalCode {
        -char[6] code
        +getRegio() char[2]
        +getWijk() char[2]
        +toString() String
    }
    class Name {
        List~String~ namen [get]
        +getVoornaam() String
        +getAchternaam() String
        +toString() String
    }
    Book "1" <-- "1" Name : auteur
    Loaner "1" <-- "1" Name
```
Voor de leesbaarheid zijn getters en setters achterwege gelaten. 
In plaats daarvan zijn deze zichtbaar gemaakt met `[get, set]`, `[get]` of `[set]` achter de relevante variabele.

Tevens zijn, om herhaling te voorkomen, 
de implementatiemethoden die gedefinieërd zijn in Interface of Abstracte klassen achterwege gelaten in de relevante implementatieklassen. 
Tenzij deze, in het geval van abstracte klassen, een non-abstracte methode overschrijven.