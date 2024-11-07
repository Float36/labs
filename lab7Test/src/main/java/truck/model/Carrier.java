package truck.model;

import truck.command.Command;

// Invoker
public class Carrier {
    Command load;
    Command search;
    Command sort;

    public Carrier(Command load, Command search, Command sort) {
        this.load = load;
        this.search = search;
        this.sort = sort;
    }

    public void loadTruck(){
        load.execute();
    }

    public void searchCoffee(){
        search.execute();
    }

    public void sortCoffee(){
        sort.execute();
    }
}
