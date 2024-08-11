package org.example;

import Services.Counter;
import Services.Registry;

import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/human_friends"; // URL используемой базы данных
    private static final String DB_USER = "root"; // имя пользователя для базы данных
    private static final String DB_PASSWORD = "123456"; // пароль пользователя для базы данных

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registry registry = new Registry(DB_URL, DB_USER, DB_PASSWORD);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Посмотреть список животных");
            System.out.println("3. Обучить животное");
            System.out.println("4. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try (Counter counter = new Counter()) {
                        registry.addAnimal(scanner, counter);
                        System.out.println("Животное успешно заведено!");
                        counter.add();
                    } catch (Exception e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 2:
                    registry.showAnimals();
                    break;
                case 3:
                    registry.trainAnimal(scanner);
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}