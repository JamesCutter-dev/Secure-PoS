package Tests;

import SecureEpos.Product.Product;
import SecureEpos.Product.ProductManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTest {

        @Test
        void testAddProduct() {
            ProductManager productManager = new ProductManager();
            Product product = new Product(1, "Test Product", 100.0, 10);
            productManager.addProduct(product);
            assertEquals(1, productManager.getProductById(1).getID()); // product added to the list of products
        }

        @Test
        void testUpdateProduct() {
            ProductManager productManager = new ProductManager();
            Product product = new Product(1, "Test Product", 100.0, 10);
            productManager.addProduct(product);
            productManager.updateProduct(1, "Updated Product", 200.0, 20, 20.0);
            assertEquals("Updated Product", productManager.getProductById(1).getName());
            assertEquals(200.0, productManager.getProductById(1).getPrice());
            assertEquals(20, productManager.getProductById(1).getQuantity());
            assertEquals(20.0, productManager.getProductById(1).getDiscount()); // product updated
        }

        @Test
        void testRemoveProduct() {
            ProductManager productManager = new ProductManager();
            Product product = new Product(1, "Test Product", 100.0, 10);
            productManager.addProduct(product); // add product to the list of products
            productManager.removeProduct(1); // remove product from the list of products
            assertNull(productManager.getProductById(1)); // product removed from the list of products
        }
}
