package SecureEpos.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager { // This class is used to manage the products
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) { // Add a product to the list
        products.add(product);
    }

    public void viewProducts() { // View the products in the list
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Product getProductById(int id) { // Get a product by its ID
        for (Product product : products) {
            if (product.getID() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void updateProduct(int id, String name, double price, int quantity, double discount) { // Update a product
        Product product = getProductById(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDiscount(discount);
        }   else {
            System.out.println("Product not found");
        }
    }

    public void removeProduct(int id)   { // Remove a product
        Product product = getProductById(id);
        if (product != null) {
            products.remove(product);
        }   else {
            System.out.println("Product not found");
        }
    }

}
