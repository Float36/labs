package truck.model;

import truck.command.Command;

public class Seller {
    Command add;
    Command show;

    public Seller(Command show, Command add) {
        this.add = add;
        this.show = show;
    }

    public void addCoffee(){
        add.execute();
    }

    public void showCoffee(){
        show.execute();
    }
}
