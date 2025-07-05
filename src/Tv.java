public class Tv extends Product implements Shippable {
    private double weight;

    public Tv(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}
