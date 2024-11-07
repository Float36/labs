package truck.command;

import truck.model.Truck;

public class SortCoffeeCommand implements Command{
    Truck truck;

    public SortCoffeeCommand(Truck truck) {
        this.truck = truck;
    }

    @Override
    public void execute(){
        truck.sortCoffee();
    }
}
