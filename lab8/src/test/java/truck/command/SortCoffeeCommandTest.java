package truck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import truck.model.Truck;

class SortCoffeeCommandTest {

    private Truck truck;
    private SortCoffeeCommand sortCoffeeCommand;

    // Створення заміни для класу Truck
    static class TruckMock extends Truck {
        boolean sortCoffeeCalled = false;

        public TruckMock(double maxVolume) {
            super(maxVolume);
        }

        @Override
        public void sortCoffee() {
            sortCoffeeCalled = true; // Прапор для перевірки, чи був викликаний метод
        }
    }

    @BeforeEach
    void setUp() {
        // Створюємо заміну для Truck
        truck = new TruckMock(10000);

        // Створюємо команду
        sortCoffeeCommand = new SortCoffeeCommand(truck);
    }

    @Test
    void testExecute_sortCoffeeCalled() {
        // Виконуємо команду
        sortCoffeeCommand.execute();

        // Перевіряємо, чи був викликаний метод sortCoffee
        assertTrue(((TruckMock) truck).sortCoffeeCalled, "Метод sortCoffee не був викликаний");
    }
}
