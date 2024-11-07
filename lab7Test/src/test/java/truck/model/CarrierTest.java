package truck.model;

import org.junit.jupiter.api.Test;
import truck.command.Command;

import static org.junit.jupiter.api.Assertions.*;

// Проста реалізація Command для тестування
class LoadCommand implements Command {
    private boolean executed = false;

    @Override
    public void execute() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

class SearchCommand implements Command {
    private boolean executed = false;

    @Override
    public void execute() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

class SortCommand implements Command {
    private boolean executed = false;

    @Override
    public void execute() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}

public class CarrierTest {

    @Test
    public void testLoadTruck() {
        LoadCommand loadCommand = new LoadCommand();
        SearchCommand searchCommand = new SearchCommand();
        SortCommand sortCommand = new SortCommand();

        Carrier carrier = new Carrier(loadCommand, searchCommand, sortCommand);

        // Перевірка, що до виконання команди loadTruck вона ще не виконана
        assertFalse(loadCommand.isExecuted());

        // Виконання методу loadTruck
        carrier.loadTruck();

        // Перевірка, що команда була виконана
        assertTrue(loadCommand.isExecuted());
    }

    @Test
    public void testSearchCoffee() {
        LoadCommand loadCommand = new LoadCommand();
        SearchCommand searchCommand = new SearchCommand();
        SortCommand sortCommand = new SortCommand();

        Carrier carrier = new Carrier(loadCommand, searchCommand, sortCommand);

        // Перевірка, що до виконання команди searchCoffee вона ще не виконана
        assertFalse(searchCommand.isExecuted());

        // Виконання методу searchCoffee
        carrier.searchCoffee();

        // Перевірка, що команда була виконана
        assertTrue(searchCommand.isExecuted());
    }

    @Test
    public void testSortCoffee() {
        LoadCommand loadCommand = new LoadCommand();
        SearchCommand searchCommand = new SearchCommand();
        SortCommand sortCommand = new SortCommand();

        Carrier carrier = new Carrier(loadCommand, searchCommand, sortCommand);

        // Перевірка, що до виконання команди sortCoffee вона ще не виконана
        assertFalse(sortCommand.isExecuted());

        // Виконання методу sortCoffee
        carrier.sortCoffee();

        // Перевірка, що команда була виконана
        assertTrue(sortCommand.isExecuted());
    }

    @Test
    public void testCarrierBehavior() {
        LoadCommand loadCommand = new LoadCommand();
        SearchCommand searchCommand = new SearchCommand();
        SortCommand sortCommand = new SortCommand();

        Carrier carrier = new Carrier(loadCommand, searchCommand, sortCommand);

        // Перевірка початкового стану
        assertFalse(loadCommand.isExecuted());
        assertFalse(searchCommand.isExecuted());
        assertFalse(sortCommand.isExecuted());

        // Виконання всіх команд
        carrier.loadTruck();
        carrier.searchCoffee();
        carrier.sortCoffee();

        // Перевірка, що всі команди були виконані
        assertTrue(loadCommand.isExecuted());
        assertTrue(searchCommand.isExecuted());
        assertTrue(sortCommand.isExecuted());
    }
}
