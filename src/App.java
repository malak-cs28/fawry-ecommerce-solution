import java.util.Date;

public class App {
    public static void main(String[] args) {
        try {
            //customer
            Customer customer = new Customer("Malak", 30000);

            //products
            Cheese cheese = new Cheese("Cheese", 100, 10, new Date(System.currentTimeMillis() + 86400000), 0.2); // Not expired
            Tv tv = new Tv("Samsung TV", 10300, 5, 3.0); // Shippable, not expirable
            ScratchCard scratchCard = new ScratchCard("Vodafone Card", 50, 10); // Not shippable

            //add products
            Cart cart = new Cart();
            cart.add(cheese, 2);       // 2 x 100 = 200
            cart.add(tv, 1);           // 1 x 10300 = 10300
            cart.add(scratchCard, 1);  // 1 x 50 = 50

            //checkout
            cart.checkout(customer);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Action: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
