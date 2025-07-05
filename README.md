# Fawry E-commerce System

### Internship Task Submission – Malak Mohamed Abdelhamid

---

## 📖 Description
A complete Java-based e-commerce system designed for the Fawry Rise Journey Full Stack Development Internship Challenge. The system allows defining various types of products, managing customer carts, handling shipping and expirable items, performing checkouts with validations, and generating shipment notices and receipts in the console.

---

## 📦 Features

- Define products with:
  - Name, price, quantity
  - Expirable status (e.g., Cheese)
  - Shippable status with weight (e.g., Cheese, TV)
- Add products to cart with specified quantity (not exceeding stock)
- Validate cart contents before checkout:
  - Expired products
  - Out-of-stock items
  - Empty cart
  - Insufficient customer balance
- Perform checkout with:
  - Subtotal calculation
  - Shipping fee calculation
  - Total amount deduction from customer balance
  - Shipment notice generation for shippable items
  - Receipt printed to console
- Custom exception handling for invalid scenarios

---

## 📑 Assumptions

- Expiry dates are stored as `Date` objects and checked at checkout.
- Shipping fee is calculated based on total shipment weight (e.g., `weight × 10 EGP` per kg).
- Product quantities are decremented upon successful checkout.
- Customer balance is initialized in code.
- All monetary values are in EGP.
- ShippingService processes only items implementing the `Shippable` interface with `getName()` and `getWeight()` methods.

---

## ✅ Tested Scenarios

- ✔ Adding valid products
- ✔ Adding expired products
- ✔ Zero quantity handling
- ✔ Out-of-stock scenarios
- ✔ Attempting checkout with an empty cart
- ✔ Insufficient customer balance
- ✔ Successful purchase with shipment notice and balance update

---

## 💻 Code Example

```java
Customer customer = new Customer("Malak", 30000);

Cheese cheese = new Cheese("Cheese", 100, 10, new Date(System.currentTimeMillis() + 86400000), 0.2); // Not expired
Tv tv = new Tv("Samsung TV", 10300, 5, 3.0);
ScratchCard scratchCard = new ScratchCard("Vodafone Card", 50, 10);

Cart cart = new Cart();
cart.add(cheese, 2);       
cart.add(tv, 1);           
cart.add(scratchCard, 1);  

cart.checkout(customer);
````

---

## 📑 Expected Console Output

```
** Shipment notice **
2x Cheese
1x Samsung TV
Total package weight 3.4kg

** Checkout receipt **
2x Cheese           200.0
1x Samsung TV      10300.0
1x Vodafone Card     50.0
-----------------------------
Subtotal           10550.0
Shipping             34.0
Amount             10584.0
Remaining balance: 19416.0
END.
```

---

## 🚀 How to Run

1. Compile all `.java` files inside the `src/` directory.
2. Run `App.java`.
3. View all output in the console.

> ✅ Java Version: 8+

---

## 📄 Submission Info

* **Full Name:** Malak Mohamed Abdelhamid
* **Internship:** Fawry Rise Journey Full Stack Development

---

## 📬 Contact

* [LinkedIn](https://www.linkedin.com/in/eng-malakmohamed)
* Email: [mmmkmalak44@gmail.com](mailto:mmmkmalak44@gmail.com)
