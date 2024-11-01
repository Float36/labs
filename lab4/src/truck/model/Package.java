package truck.model;

// Клас для збереження даних про упаковку
public class Package {
    private String material;
    private double weight;
    private double volume;

    public Package(String material, double weight, double volume) {
        this.material = material;
        this.weight = weight;
        this.volume = volume;
    }

    public String getMaterial() {
        return material;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return String.format("Упаковка: матеріал = %s, вага = %.2f г, об'єм = %.2f л", material, weight, volume);
    }
}
