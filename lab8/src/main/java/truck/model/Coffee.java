package truck.model;

public class Coffee {
    private String name;
    private double weight;
    private double price;
    private double volume;
    private Package packaging;
    private int quantity; // Кількість упаковок
    private int quantityInTruck = 0;

    public Coffee(String name, double weight, double price, double volume, Package packaging, int quantity) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.volume = volume;
        this.packaging = packaging;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityInTruck() {
        return quantityInTruck;
    }

    public void setQuantityInTruck(int quantityInTruck) {
        this.quantityInTruck = quantityInTruck;
    }

    public double getTotalVolume() {
        return packaging.getVolume();
    }

    @Override
    public String toString() {
        return String.format("%s: вага = %.2f г, ціна = %.2f грн, об'єм = %.2f л, кількість упаковок = %d",
                name, weight, price, packaging.getVolume(), quantity);
    }

    public String toStringInTruck() {
        return String.format("%s: вага = %.2f г, ціна = %.2f грн, об'єм = %.2f л, кількість упаковок = %d",
                name, weight, price, packaging.getVolume(), quantityInTruck);
    }

}
