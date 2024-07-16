```mermaid
classDiagram 
    direction TB
    class Boek {
        -UUID id [get]
        -ISBN ISBN [get]
        -Naam auteur [get]
        -String titel [get]
        -String notities [get]
    }
    Boek "1" --o "1" Lening
    ISBN "1" <-- "1..*" Boek
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
    Lening "0..*" -- "1" Lener
    class Lening {
        -Boek geleendBoek [get]
        -LocalDateTime leenMoment [get]
    }
    Boek "1" --o "1" Reservering
    Reservering "0..*" -- "1" Lener
    class Reservering {
        -Boek gereserveerdBoek [get]
        -LocalDateTime reserveringsMoment [get]
    }
    class Lener {
        -UUID id [get]
        -Naam naam [get]
        -Adres adres [get]
        -String notities [get]
        -List~Lening~ leningen [get]
        -List~Reservering~ reserveringen [get]
    }
    Lener "1..*" *-- "1" Adres
    class Adres {
        -Huisnummer huisnummer [get]
        -String straatNaam [get]
        -String plaatsNaam [get]
        -String provincie [get]
        -Postcode postcode [get]
    }
    Adres "1" -- "1" Huisnummer
    class Huisnummer {
        -Integer nummer [get]
        -String toevoeging [get]
        +getNummer() Integer
        +getToevoeging() String
        +toString() String
    }
    Adres "0..*" -- "1" Postcode
    class Postcode {
        -char[6] code
        +getRegio() char[2]
        +getWijk() char[2]
        +toString() String
    }
    class Naam {
        List~String~ namen [get]
        +getVoornaam() String
        +getAchternaam() String
        +toString() String
    }
    Boek <-- Naam
    Lener *-- Naam
```
Voor de leesbaarheid zijn getters en setters achterwege gelaten. 
In plaats daarvan zijn deze zichtbaar gemaakt met `[get, set]`, `[get]` of `[set]` achter de relevante variabele.

Tevens zijn, om herhaling te voorkomen, 
de implementatiemethoden die gedefinieërd zijn in Interface of Abstracte klassen achterwege gelaten in de relevante implementatieklassen. 
Tenzij deze, in het geval van abstracte klassen, een non-abstracte methode overschrijven.