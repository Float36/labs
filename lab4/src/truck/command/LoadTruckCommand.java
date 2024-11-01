package truck.command;

import truck.model.Truck;
import truck.model.Coffee;

public class LoadTruckCommand implements Command{
    private Truck truck;
    private Coffee coffee;
    private int quantity;

    public LoadTruckCommand(Truck truckManager) {
        this.truck = truckManager;
    }

    // Метод для встановлення кави, яку необхідно завантажити
    public void setCoffee(Coffee coffee, int quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }

    @Override
    public void execute(){
        if (coffee != null && quantity > 0) {
            truck.loadTruck(coffee, quantity);
        } else {
            System.out.println("Необхідно встановити каву та кількість перед завантаженням.");
        }
    }
}
