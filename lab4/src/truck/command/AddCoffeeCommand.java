package truck.command;

import truck.model.Shop;

public class AddCoffeeCommand implements Command{
    Shop shop;

    public AddCoffeeCommand(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void execute(){
        shop.addCoffee();
    }
}
