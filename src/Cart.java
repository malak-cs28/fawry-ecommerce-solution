import java.util.List;
import java.util.ArrayList;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }


   public void add(Product product, int quantity) {
    if (quantity <= 0) {
        throw new IllegalArgumentException("Quantity must be at least 1.");
    }

    if (product.isExpired()) {
        throw new IllegalArgumentException("Cannot add '" + product.getName() + "' — it is expired.");
    }

    if (quantity > product.getQuantity()) {
        throw new IllegalArgumentException(" Cannot add '" + product.getName() + "' — only " + product.getQuantity() + " in stock.");
    }

    items.add(new CartItem(product, quantity));
}


    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }


    public double getShippingFees() {
        double totalWeight = 0;
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product.isShippable() && product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                totalWeight += shippable.getWeight() * item.getQuantity();
            }
        }
        return totalWeight * 10; // 10 EGP per kg
    }

    public void checkout(Customer customer) {
        if (items.isEmpty()) {
            throw new IllegalStateException("Your cart is empty. Please add items before checking out.");
        }

        // Check all items
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product.isExpired()) {
                throw new IllegalStateException("Cannot checkout — '" + product.getName() + "' is expired.");
            }
            if (item.getQuantity() > product.getQuantity()) {
                throw new IllegalStateException("Cannot checkout — Not enough stock for '" + product.getName() + "'.");
            }
        }

        double subtotal = getSubtotal();
        double shipping = getShippingFees();
        double total = subtotal + shipping;

        if (customer.getBalance() < total) {
            throw new IllegalStateException("Insufficient balance. Total amount is " + total + " but customer has " + customer.getBalance());
        }

        // Deduct balance and update product quantities
        customer.deduct(total);
        for (CartItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        //shipment
        List<Shippable> shippableItems = new ArrayList<>();
        double totalWeight = 0;

        System.out.println("** Shipment notice **");
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof Shippable) {
                Shippable s = (Shippable) product;
                System.out.println(item.getQuantity() + "x " + product.getName());
                totalWeight += s.getWeight() * item.getQuantity();
                shippableItems.add(s);
            }
        }
        if (!shippableItems.isEmpty()) {
            System.out.println("Total package weight: " + totalWeight + "kg");
        }

        //receipt
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getSubtotal());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Shipping: " + shipping);
        System.out.println("Amount: " + total);
        System.out.println("Customer balance after payment: " + customer.getBalance());
    }
}
