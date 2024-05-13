package SecureEpos;
import java.util.ArrayList;
import java.util.List;

public class Cart { // This class is used to manage the cart
    private List<CartItem> items;
    private double discount;

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

    public void removeItem(Product product) { // Remove an item from the cart
        items.removeIf(item -> item.getProduct().getID() == product.getID());
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

    public double processPayment(double amountPaid) { // Process the payment and return the change
        double total = calculateTotal();
        return amountPaid - total;
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
