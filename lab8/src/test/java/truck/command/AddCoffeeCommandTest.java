package truck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import truck.model.Coffee;
import truck.model.Package;
import truck.model.Shop;
import truck.command.AddCoffeeCommand;

class AddCoffeeCommandTest {

    private Shop shop;
    private AddCoffeeCommand addCoffeeCommand;

    @BeforeEach
    void setUp() {
        // Ініціалізація об'єктів перед кожним тестом
        shop = new Shop();
        addCoffeeCommand = new AddCoffeeCommand(shop);
    }

    @Test
    void testExecute_withValidCoffee() {
        // Створення об'єкта кави
        truck.model.Package package1 = new Package("Банка", 0.5, 1.0);
        Coffee coffee = new Coffee("Arabica", 500, 100, 1, package1, 1); // Назва, вага, ціна
        addCoffeeCommand.setCoffee(coffee);

        // Виконання команди додавання кави
        addCoffeeCommand.execute();

        // Перевірка, чи каву було додано до магазину
        assertTrue(shop.getAvailableCoffee().contains(coffee), "Кава повинна бути додана до магазину");
    }

    @Test
    void testExecute_withoutCoffee() {
        // Підготовка для тесту, коли кава не була встановлена
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Зберігаємо оригінальний потік виведення
        System.setOut(new PrintStream(outputStream)); // Перенаправляємо потік виведення

        // Виконання команди додавання кави без її встановлення
        addCoffeeCommand.execute();

        // Перевірка, що виведено правильне повідомлення
        String output = outputStream.toString().trim();
        assertEquals("Необхідно встановити каву перед додаванням.", output);

        // Відновлення оригінального потоку виведення
        System.setOut(originalOut);
    }
}
