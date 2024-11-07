package truck.command;

import truck.model.Shop;

public class ShowAvailableCoffeeCommand implements Command{
    Shop shop;

    public ShowAvailableCoffeeCommand(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void execute(){
        shop.showAvailableCoffee();
    }
}
