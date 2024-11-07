package truck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import truck.model.Shop;

class ShowAvailableCoffeeCommandTest {

    private Shop shop;
    private ShowAvailableCoffeeCommand showAvailableCoffeeCommand;

    // Створення заміни для класу Shop
    static class ShopMock extends Shop {
        boolean showAvailableCoffeeCalled = false;

        @Override
        public void showAvailableCoffee() {
            showAvailableCoffeeCalled = true; // Прапор для перевірки, чи був викликаний метод
        }
    }

    @BeforeEach
    void setUp() {
        // Створюємо заміну для Shop
        shop = new ShopMock();

        // Створюємо команду
        showAvailableCoffeeCommand = new ShowAvailableCoffeeCommand(shop);
    }

    @Test
    void testExecute_showAvailableCoffeeCalled() {
        // Виконуємо команду
        showAvailableCoffeeCommand.execute();

        // Перевіряємо, чи був викликаний метод showAvailableCoffee
        assertTrue(((ShopMock) shop).showAvailableCoffeeCalled, "Метод showAvailableCoffee не був викликаний");
    }
}
