package truck.command;

import truck.model.Truck;

public class LoadTruckCommand implements Command{
    Truck truck;

    public LoadTruckCommand(Truck truckManager) {
        this.truck = truckManager;
    }

    @Override
    public void execute(){
        truck.loadTruck();
    }
}
