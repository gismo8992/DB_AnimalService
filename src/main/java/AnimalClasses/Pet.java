package AnimalClasses;

import java.time.LocalDate;

public abstract class Pet extends Animal {
    public Pet(String name, LocalDate birthDate) {
        super(name, birthDate, "Домашнее животное");
    }
}
