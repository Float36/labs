package truck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import truck.model.Coffee;
import truck.model.Shop;
import truck.model.GrainCoffee;
import truck.model.Package;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop();
    }

    @Test
    void testInitializeDefaultCoffees() {
        // Перевіряємо, що за замовчуванням додається 3 види кави
        List<Coffee> coffees = shop.getAvailableCoffee();
        assertEquals(3, coffees.size(), "Кількість кави за замовчуванням має бути 3");
    }

    @Test
    void testAddCoffee() {
        Package package1 = new Package("Банка", 0.5, 1.0);
        Coffee newCoffee = new GrainCoffee("Нова зернова кава", 500, 120, 1.2, package1, 1000);

        shop.addCoffee(newCoffee);
        List<Coffee> coffees = shop.getAvailableCoffee();

        assertTrue(coffees.contains(newCoffee), "Кава повинна бути додана до списку доступної кави");
        assertEquals(4, coffees.size(), "Кількість кави після додавання має бути 4");
    }

    @Test
    void testShowAvailableCoffeeWhenNotEmpty() {
        // Захоплюємо стандартний вивід
        List<Coffee> coffees = shop.getAvailableCoffee();
        assertFalse(coffees.isEmpty(), "Список кави не має бути порожнім");

        // Викликаємо метод showAvailableCoffee і переконуємося, що він відображає доступну каву
        shop.showAvailableCoffee();
    }

    @Test
    void testShowAvailableCoffeeWhenEmpty() {
        // Очищаємо список кави, щоб перевірити випадок з порожнім списком
        shop.getAvailableCoffee().clear();

        assertTrue(shop.getAvailableCoffee().isEmpty(), "Список кави має бути порожнім");

        // Викликаємо метод showAvailableCoffee і переконуємося, що вивід правильний
        shop.showAvailableCoffee();
    }

    @Test
    void testLoadCoffeesFromFile() throws IOException {
        // Створюємо тестовий файл для завантаження
        String testFilePath = "test_coffee_file.txt";
        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write("Зернова кава,500,100,1,Банка,0.5,1.0,1000\n");
            writer.write("Мелена кава,260,50,0.5,Пакет,0.2,0.5,500\n");
            writer.write("Розчинна кава,100,30,0.25,Пакет,0.2,0.5,2000\n");
        }

        // Перевіряємо початкову кількість кави
        int initialSize = shop.getAvailableCoffee().size();

        // Завантажуємо каву з файлу
        shop.loadCoffeesFromFile(testFilePath);

        // Перевіряємо, що кількість кави збільшилася
        assertEquals(initialSize + 3, shop.getAvailableCoffee().size(), "Кількість кави має збільшитися на 3 після завантаження з файлу");

        // Видаляємо тестовий файл після тесту
        new java.io.File(testFilePath).delete();
    }
}