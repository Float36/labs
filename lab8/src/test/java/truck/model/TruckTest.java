package truck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TruckTest {
    private Truck truck;
    private Coffee coffee1;
    private Coffee coffee2;
    private Coffee coffee3;

    @BeforeEach
    void setUp() {
        // Ініціалізуємо фургон з максимальною ємністю 500 літрів
        truck = new Truck(500);

        // Ініціалізуємо зразки кави
        coffee1 = new Coffee("Arabica", 1.0, 100.0, 2.0, new Package("Plastic", 0.1, 2.0), 10);
        coffee2 = new Coffee("Robusta", 0.5, 50.0, 1.0, new Package("Metal", 0.05, 1.0), 20);
        coffee3 = new Coffee("Liberica", 2.0, 200.0, 3.0, new Package("Glass", 0.2, 3.0), 5);
    }

    @Test
    void testLoadTruck_Success() {
        // Завантажуємо фургон
        truck.loadTruck(coffee1, 5);

        // Перевіряємо, що кількість кави у фургоні оновилася
        assertEquals(5, coffee1.getQuantityInTruck());

        // Перевіряємо, що об'єм фургона оновився
        assertTrue(truck.getCurrentVolume() <= truck.getMaxVolume());
    }

    @Test
    void testLoadTruck_NotEnoughSpace() {
        // Спробуємо завантажити більше, ніж максимальний об'єм фургона
        truck.loadTruck(coffee3, 20000);

        // Перевіряємо, що кількість кави у фургоні не змінилася
        assertEquals(0, coffee3.getQuantityInTruck());

        // Перевіряємо, що об'єм фургона не перевищив максимальний об'єм
        assertTrue(truck.getCurrentVolume() <= truck.getMaxVolume());
    }

    @Test
    void testSortCoffee() {
        // Додаємо декілька видів кави у фургон
        truck.loadTruck(coffee1, 5);
        truck.loadTruck(coffee2, 10);
        truck.loadTruck(coffee3, 2);

        // Виконуємо сортування кави
        truck.sortCoffee();

        // Перевіряємо порядок сортування за співвідношенням ціни до ваги
        List<Coffee> loadedCoffees = truck.getLoadedCoffees();
        assertTrue(loadedCoffees.get(0).getPrice() / loadedCoffees.get(0).getWeight()
                <= loadedCoffees.get(1).getPrice() / loadedCoffees.get(1).getWeight());
    }

    @Test
    void testSearchCoffee_Found() {
        // Додаємо каву у фургон
        truck.loadTruck(coffee1, 5);

        // Встановлюємо введення для емуляції пошуку кави
        String input = "Arabica\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Перевіряємо пошук кави
        truck.searchCoffee();

        // Перевіряємо, що кава знайдена
        assertTrue(truck.getLoadedCoffees().stream().anyMatch(coffee -> coffee.getName().equals("Arabica")));
    }

    @Test
    void testSearchCoffee_NotFound() {
        // Встановлюємо введення для емуляції пошуку кави
        String input = "NonExistingCoffee\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Перевіряємо, що кава не знайдена
        truck.searchCoffee();

        // Перевіряємо, що кава не була додана
        assertFalse(truck.getLoadedCoffees().stream().anyMatch(coffee -> coffee.getName().equals("NonExistingCoffee")));
    }

    @Test
    void testGetCurrentVolume() {
        // Завантажуємо фургон, щоб збільшити об'єм
        truck.loadTruck(coffee1, 5);

        // Перевіряємо, що getCurrentVolume повертає коректний об'єм
        double expectedVolume = coffee1.getTotalVolume() * 5;
        assertEquals(expectedVolume, truck.getCurrentVolume(), 0.001);
    }

    @Test
    void testGetLoadedCoffees() {
        // Завантажуємо фургон
        truck.loadTruck(coffee1, 5);

        // Перевіряємо, що завантажена кава міститься в списку
        List<Coffee> loadedCoffees = truck.getLoadedCoffees();
        assertTrue(loadedCoffees.contains(coffee1));
    }

    @Test
    void testGetMaxVolume() {
        // Перевіряємо, що getMaxVolume повертає правильний максимальний об'єм
        assertEquals(500, truck.getMaxVolume(), 0.001);
    }
}