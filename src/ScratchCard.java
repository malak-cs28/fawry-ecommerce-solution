public class ScratchCard extends Product {
    public ScratchCard(String name, double price, int quantity) {
    super(name, price, quantity);
}

@Override
public boolean isShippable() {
    return false;
}

}
