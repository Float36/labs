package truck.command;

import truck.model.Shop;
import truck.model.Coffee;

public class AddCoffeeCommand implements Command {
    private Shop shop;
    private Coffee coffee;

    public AddCoffeeCommand(Shop shop) {
        this.shop = shop;
    }

    // Метод для встановлення конкретної кави, яку потрібно додати
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void execute() {
        if (coffee != null) {
            shop.addCoffee(coffee);
        } else {
            System.out.println("Необхідно встановити каву перед додаванням.");
        }
    }
}
