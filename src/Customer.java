public class Customer {
    private String customerName;
    private double balance;

    public Customer(String customerName, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.customerName = customerName;
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deduct(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deduct amount cannot be negative.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance.");
        }
        this.balance -= amount;
    }
}
