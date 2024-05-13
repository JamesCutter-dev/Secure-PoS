package SecureEpos;

public class CartItem { // This class is used to represent an item in the cart
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) { // Constructor
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; } // Getters and setters
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { // Calculate the total price of the item
        return product.getPriceAfterDiscount() * quantity;
    }

    @Override
    public String toString() { // Override the toString method to return a string representation of the object
        return "Product: " + product.getName() + ", Quantity: " + quantity + ", Total Price: " + getTotalPrice();    }

}
