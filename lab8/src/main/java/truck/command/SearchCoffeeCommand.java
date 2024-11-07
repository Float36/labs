package truck.command;

import truck.model.Truck;

public class SearchCoffeeCommand implements Command{
    Truck truck;

    public SearchCoffeeCommand(Truck truck) {
        this.truck = truck;
    }

    @Override
    public void execute(){
        truck.searchCoffee();
    }
}
