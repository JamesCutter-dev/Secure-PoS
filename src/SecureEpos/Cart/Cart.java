package SecureEpos.Cart;
import SecureEpos.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart { // This class is used to manage the cart
    private List<CartItem> items;
    private double discount;
    public boolean hasItem(Product product, int quantity) {
        // Check if the product exists in the cart with at least 'quantity' amount
        return items.stream().anyMatch(item -> item.getProduct().equals(product) && item.getQuantity() >= quantity);
    }

    public Cart() { // Constructor
        items = new ArrayList<>();
        discount = 0.0;
    }

    public List<CartItem> getItems(){
        return items;
    }

    public double getDiscount() {
        return discount;
    }

    public void addItem (Product product, int quantity) { // Add an item to the cart
        CartItem cartItem = new CartItem(product, quantity);
        items.add(cartItem);
    }

    public void removeItem(Product product, int quantity) {
        // Find the cart item and reduce its quantity or remove it entirely
        CartItem item = items.stream().filter(i -> i.getProduct().equals(product)).findFirst().orElse(null);
        if (item != null) {
            if (item.getQuantity() > quantity) {
                item.setQuantity(item.getQuantity() - quantity);
            } else {
                items.remove(item);
            }
        }
    }

    public void viewCart() { // View the items in the cart
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.println(calculateTotal());
    }

    public void applyDiscount(double discount) { // Apply a discount to the cart
        this.discount = discount;
    }

    public double calculateTotal() { // Calculate the total price of the items in the cart
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total - (total * discount / 100);
    }
    public double getTotal() { // Calculate the total price of the items in the cart
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public double processPayment(double amountPaid) { // Process the payment and return the change 1.2 added IllegalArgumentException
        try {
            double total = calculateTotal();
            if (amountPaid < total) {
                throw new IllegalArgumentException("Insufficient payment");
            }
            return amountPaid - total;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void generateReceipt()   { // Generate a receipt for the items in the cart
        System.out.println("----- Receipt -----");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.println("Total (after discount): £" + calculateTotal());
        System.out.println("-------------------");
    }

    public void checkout()  { // Checkout the items in the cart
        generateReceipt();
        items.clear();
    }
}
