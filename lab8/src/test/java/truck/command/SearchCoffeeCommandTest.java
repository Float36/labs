package truck.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import truck.model.Truck;

class SearchCoffeeCommandTest {

    private Truck truck;
    private SearchCoffeeCommand searchCoffeeCommand;

    // Клас TruckMock для заміни реального Truck
    static class TruckMock extends Truck {
        boolean searchCoffeeCalled = false;

        public TruckMock(double maxVolume) {
            super(maxVolume);
        }

        @Override
        public void searchCoffee() {
            searchCoffeeCalled = true; // Поставимо прапор, коли метод буде викликаний
        }
    }

    @BeforeEach
    void setUp() {
        // Створюємо заміну для Truck
        truck = new TruckMock(10000);

        // Створюємо команду
        searchCoffeeCommand = new SearchCoffeeCommand(truck);
    }

    @Test
    void testExecute_searchCoffeeCalled() {
        // Виконуємо команду
        searchCoffeeCommand.execute();

        // Перевіряємо, чи був викликаний метод searchCoffee
        assertTrue(((TruckMock) truck).searchCoffeeCalled, "Метод searchCoffee не був викликаний");
    }
}
