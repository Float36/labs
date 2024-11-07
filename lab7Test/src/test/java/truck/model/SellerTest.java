package truck.model;

import org.junit.jupiter.api.Test;
import truck.command.Command;

import static org.junit.jupiter.api.Assertions.*;

// Проста реалізація Command для тестування
class AddCommand implements Command {
    private boolean executed = false;

    @Override
    public void execute() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

class ShowCommand implements Command {
    private boolean executed = false;

    @Override
    public void execute() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

public class SellerTest {

    @Test
    public void testAddCoffee() {
        AddCommand addCommand = new AddCommand();
        ShowCommand showCommand = new ShowCommand();

        Seller seller = new Seller(showCommand, addCommand);

        // Перевірка, що до виконання команди addCoffee вона ще не виконана
        assertFalse(addCommand.isExecuted());

        // Виконання методу addCoffee
        seller.addCoffee();

        // Перевірка, що команда була виконана
        assertTrue(addCommand.isExecuted());
    }

    @Test
    public void testShowCoffee() {
        AddCommand addCommand = new AddCommand();
        ShowCommand showCommand = new ShowCommand();

        Seller seller = new Seller(showCommand, addCommand);

        // Перевірка, що до виконання команди showCoffee вона ще не виконана
        assertFalse(showCommand.isExecuted());

        // Виконання методу showCoffee
        seller.showCoffee();

        // Перевірка, що команда була виконана
        assertTrue(showCommand.isExecuted());
    }

    @Test
    public void testSellerBehavior() {
        AddCommand addCommand = new AddCommand();
        ShowCommand showCommand = new ShowCommand();

        Seller seller = new Seller(showCommand, addCommand);

        // Перевірка початкового стану
        assertFalse(addCommand.isExecuted());
        assertFalse(showCommand.isExecuted());

        // Виконання всіх команд
        seller.addCoffee();
        seller.showCoffee();

        // Перевірка, що всі команди були виконані
        assertTrue(addCommand.isExecuted());
        assertTrue(showCommand.isExecuted());
    }
}
