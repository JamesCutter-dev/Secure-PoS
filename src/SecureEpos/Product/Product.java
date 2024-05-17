package SecureEpos.Product;

public class Product { // This class is used to represent a product
    private int id;
    private String name;
    private double price;
    private int quantity;
    private double discount;

    public Product(int id, String name, double price, int quantity) { // Constructor
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = 0.0;
    }

    public int getID() { return id;} // Getters and setters
    public String getName() { return name;}
    public String setName(String name) { return this.name = name;}
    public double getPrice() { return price;}
    public double setPrice(double price) { return this.price = price;}
    public int getQuantity() { return quantity;}
    public void setQuantity(int quantity) { this.quantity = quantity;}
    public double getDiscount() { return discount;}
    public void setDiscount(double discount) { this.discount = discount;}

    public double getPriceAfterDiscount() { // Calculate the price after discount
        return price - price * discount / 100;

    }

    @Override
    public String toString() { // Override the toString method to return a string representation of the object
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Discount: " + discount + "%";    }
}
