import java.util.Date;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("==== E-Commerce System Test ====\n");

        // Test 1: Normal Successful Checkout
        try {
            Customer c1 = new Customer("Malak", 30000);
            Cart cart1 = new Cart();
            Cheese cheese = new Cheese("Cheese", 100, 10, new Date(System.currentTimeMillis() + 86400000), 0.2);
            Tv tv = new Tv("TV", 10300, 5, 3.0);
            cart1.add(cheese, 2);
            cart1.add(tv, 1);
            cart1.checkout(c1);
            System.out.println("Test 1 Passed: Normal Checkout\n");
        } catch (Exception e) {
            System.out.println("Test 1 Failed: " + e.getMessage() + "\n");
        }

        // Test 2: Add Expired Product
        try {
            Cart cart2 = new Cart();
            Cheese expiredCheese = new Cheese("Old Cheese", 80, 5, new Date(System.currentTimeMillis() - 86400000), 0.2);
            cart2.add(expiredCheese, 1);
            System.out.println("Test 2 Failed: Expired product was added\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 2 Passed: " + e.getMessage() + "\n");
        }

        // Test 3: Add More Than Available Quantity
        try {
            Cart cart3 = new Cart();
            Tv tv2 = new Tv("LG TV", 5000, 2, 2.5);
            cart3.add(tv2, 3);
            System.out.println("Test 3 Failed: Over-quantity added\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 3 Passed: " + e.getMessage() + "\n");
        }

        // Test 4: Add Product with Zero Quantity
        try {
            Cart cart4 = new Cart();
            Cheese cheese2 = new Cheese("Cheddar", 90, 5, new Date(System.currentTimeMillis() + 86400000), 0.3);
            cart4.add(cheese2, 0);
            System.out.println("Test 4 Failed: Zero quantity accepted\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 4 Passed: " + e.getMessage() + "\n");
        }

        // Test 5: Checkout with Empty Cart
        try {
            Cart cart5 = new Cart();
            Customer c5 = new Customer("Empty Guy", 500);
            cart5.checkout(c5);
            System.out.println("Test 5 Failed: Empty cart checked out\n");
        } catch (IllegalStateException e) {
            System.out.println("Test 5 Passed: " + e.getMessage() + "\n");
        }

        // Test 6: Checkout with Expired Product in Cart
        try {
            Cart cart6 = new Cart();
            Cheese expiredCheese2 = new Cheese("Rotten Cheese", 70, 4, new Date(System.currentTimeMillis() - 1000000), 0.2);
            cart6.add(expiredCheese2, 1);
            Customer c6 = new Customer("Tester", 5000);
            cart6.checkout(c6);
            System.out.println("Test 6 Failed: Expired item checked out\n");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Test 6 Passed: " + e.getMessage() + "\n");
        }

        // Test 7: Checkout with Insufficient Balance
        try {
            Customer c7 = new Customer("Broke", 100);
            Cart cart7 = new Cart();
            Tv expensiveTV = new Tv("Luxury TV", 5000, 3, 5.0);
            cart7.add(expensiveTV, 1);
            cart7.checkout(c7);
            System.out.println("Test 7 Failed: Checkout allowed with low balance\n");
        } catch (IllegalStateException e) {
            System.out.println("Test 7 Passed: " + e.getMessage() + "\n");
        }

        // Test 8: Reduce Quantity Below Zero
        try {
            Cheese cheese3 = new Cheese("Test Cheese", 40, 2, new Date(System.currentTimeMillis() + 1000000), 0.2);
            cheese3.reduceQuantity(3);
            System.out.println("Test 8 Failed: Reduced quantity below zero\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 8 Passed: " + e.getMessage() + "\n");
        }

        System.out.println("==== All Tests Completed ====");
    }
}
