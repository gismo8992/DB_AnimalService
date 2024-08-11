package Services;

/**
 * Класс для реализации функционала подсчета животных. Реализует интерфейс AutoCloseable
 */
public class Counter implements AutoCloseable {
    private int count; // переменная для учета добавленного животного

    /**
     * Метод для учета добавленного животного и увеличения переменной count на 1.
     */
    public void add() {
        count++;
    }

    /**
     * Метод для получения информации о количестве добавленных животных.
     *
     * @return count - количество добавленных животных
     */
    private int getCount() {
        return count;
    }

    @Override
    public void close() throws Exception {
        if (count > 0) {
            System.out.println("Счетчик закрыт. Текущее значение: " + count);
        } else {
            throw new IllegalStateException("Счетчик не использовался.");
        }
    }
}
