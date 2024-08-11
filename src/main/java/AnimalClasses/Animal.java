package AnimalClasses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, определяющий свойства и методы для абстрактного животного.
 */
public abstract class Animal {
    private String name; // переменная для хранения имени животного
    private LocalDate birthDate; // переменная для хранения даты рождения животного
    private List<String> commands; // переменная для хранения команд, которым обучено животное
    private String type; // переменная для хранения типа животного

    public Animal(String name, LocalDate birthDate, String type) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getType() {
        return type;
    }

    /**
     * Метод выводит список команд, которым обучено животное.
     */
    public void showCommands() {
        if (commands.isEmpty()) {
            System.out.println("Животное не обучено командам.");
        } else {
            System.out.println("Список команд:");
            for (String command : commands) {
                System.out.println("- " + command);
            }
        }
    }

}
