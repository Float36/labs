package truck.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import truck.model.Coffee;
import truck.model.Package;
import truck.model.Truck;
import truck.command.LoadTruckCommand;

class LoadTruckCommandTest {

    private Truck truck;
    private LoadTruckCommand loadTruckCommand;

    @BeforeEach
    void setUp() {
        // Ініціалізація об'єктів перед кожним тестом
        truck = new Truck(10000);
        loadTruckCommand = new LoadTruckCommand(truck);
    }

    @Test
    void testExecute_withValidCoffeeAndQuantity() {
        // Створення об'єкта кави
        truck.model.Package package1 = new Package("Банка", 0.5, 1.0);
        Coffee coffee = new Coffee("Robusta", 500, 100, 1, package1, 20);
        int quantity = 10;
        loadTruckCommand.setCoffee(coffee, quantity);

        // Виконання команди завантаження кави в фургон
        loadTruckCommand.execute();

        // Перевірка, чи кава була додана в фургон
        assertTrue(truck.getLoadedCoffees().contains(coffee), "Кава повинна бути завантажена в фургон");
        assertEquals(quantity, coffee.getQuantity(), "Кількість кави повинна бути правильна");
    }

    @Test
    void testExecute_withoutCoffee() {
        // Підготовка для тесту, коли кава не була встановлена
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Зберігаємо оригінальний потік виведення
        System.setOut(new PrintStream(outputStream)); // Перенаправляємо потік виведення

        // Виконання команди завантаження без встановленої кави
        loadTruckCommand.execute();

        // Перевірка, що виведено правильне повідомлення
        String output = outputStream.toString().trim();
        assertEquals("Необхідно встановити каву та кількість перед завантаженням.", output);

        // Відновлення оригінального потоку виведення
        System.setOut(originalOut);
    }

    @Test
    void testExecute_withoutQuantity() {
        // Створення об'єкта кави, але без кількості
        truck.model.Package package1 = new Package("Банка", 0.5, 1.0);
        Coffee coffee = new Coffee("Robusta", 500, 100, 1, package1, 1);
        loadTruckCommand.setCoffee(coffee, 0); // Кількість = 0

        // Підготовка для тесту, коли кількість не була встановлена коректно
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Зберігаємо оригінальний потік виведення
        System.setOut(new PrintStream(outputStream)); // Перенаправляємо потік виведення

        // Виконання команди завантаження без встановленої кількості
        loadTruckCommand.execute();

        // Перевірка, що виведено правильне повідомлення
        String output = outputStream.toString().trim();
        assertEquals("Необхідно встановити каву та кількість перед завантаженням.", output);

        // Відновлення оригінального потоку виведення
        System.setOut(originalOut);
    }
}
