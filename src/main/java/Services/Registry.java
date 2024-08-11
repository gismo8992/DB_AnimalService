package Services;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Класс для реализации функционала добавления животных, вывода животных из базы данных, тренировки животных.
 */
public class Registry {
    private String dbUrl; // URL используемой базы данных
    private String dbUser; // имя пользователя для базы данных
    private String dbPassword; // пароль пользователя для базы данных

    public Registry(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * Метод для добавления животного в базу данных.
     *
     * @param scanner объект класса типа Scanner для считывания вводимых данных из консоли.
     * @param counter объект класса типа Counter для счета добавленных животных.
     */
    public void addAnimal(Scanner scanner, Counter counter) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO new_table_animal (name, birth_date, type) VALUES (?, ?, ?)")) {
            System.out.print("Введите имя животного: ");
            String name = scanner.nextLine();
            System.out.print("Введите дату рождения животного (ДД.MM.ГГГГ): ");
            String birthDateString = scanner.nextLine();
            LocalDate birthDate = LocalDate.parse(birthDateString, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            System.out.print("Выберите тип животного:\n1. Домашнее\n2. Вьючное\n");
            int typeChoice = scanner.nextInt();
            scanner.nextLine();
            String type = "";
            switch (typeChoice) {
                case 1:
                    System.out.print("Выберите вид домашнего животного:\n1. Кот\n2. Собака\n3. Хомяк\n");
                    int domesticChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (domesticChoice) {
                        case 1:
                            type = "Кот";
                            break;
                        case 2:
                            type = "Собака";
                            break;
                        case 3:
                            type = "Хомяк";
                            break;
                        default:
                            System.out.println("Неверный выбор животного.");
                            return;
                    }
                    break;
                case 2:
                    System.out.print("Выберите вид вьючного животного:\n1. Лошадь\n2. Осел\n3. Верблюд\n");
                    int packChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (packChoice) {
                        case 1:
                            type = "Лошадь";
                            break;
                        case 2:
                            type = "Осел";
                            break;
                        case 3:
                            type = "Верблюд";
                            break;
                        default:
                            System.out.println("Неверный выбор животного.");
                            return;
                    }
                    break;
                default:
                    System.out.println("Неверный выбор типа животного.");
                    return;
            }

            statement.setString(1, name);
            statement.setDate(2, Date.valueOf(birthDate));
            statement.setString(3, type);
            statement.executeUpdate();
            counter.add();
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении животного: " + e.getMessage());
        }

    }

    /**
     * Метод для вывода всех животных из базы данных.
     */
    public void showAnimals() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM new_table_animal")) {
            if (!resultSet.isBeforeFirst()) {
                System.out.println("В реестре нет животных.");
                return;
            }
            System.out.println("\nСписок животных:");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Date birthDate = resultSet.getDate("birth_date");
                String type = resultSet.getString("type");
                System.out.println("- " + type + ": " + name + ", дата рождения: " + birthDate);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении списка животных: " + e.getMessage());
        }
    }

    public void trainAnimal(Scanner scanner) {
        System.out.print("Введите имя животного для обучения: ");
        String animalName = scanner.nextLine();
        System.out.print("Введите команду для обучения: ");
        String command = scanner.nextLine();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement statement = connection.prepareStatement("UPDATE animals SET commands = CONCAT(commands, ?, ',') WHERE name = ?")) {
            statement.setString(1, command);
            statement.setString(2, animalName);
            statement.executeUpdate();
            System.out.println("Животное обучено новой команде.");
        } catch (SQLException e) {
            System.err.println("Ошибка при обучении животного: " + e.getMessage());
        }
    }
}
