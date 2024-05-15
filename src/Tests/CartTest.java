package Tests;

import SecureEpos.Cart;
import SecureEpos.Product;
import SecureEpos.CartItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void testAddItem() {
        Cart cart = new Cart();
        Product product = new Product(1, "Test Product", 100.0, 10);
        cart.addItem(product, 5);
        assertEquals(1, cart.getItems().size());
    }

//    @Test
//    void testRemoveItem() {
//        Cart cart = new Cart();
//        Product product = new Product(1, "Test Product", 100.0, 10);
//        cart.addItem(product, 5);
//        cart.removeItem(product);
//        assertEquals(0, cart.getItems().size());
//   }
    @Test
    void testApplyDiscount() {
        Cart cart = new Cart();
        cart.applyDiscount(10.0);
        assertEquals(10, cart.getDiscount());
    }

    @Test
    void testCalculateTotalPrice() {
        Cart cart = new Cart();
        Product product = new Product(1, "Test Product", 100.0, 10);
        cart.addItem(product, 5);
        assertEquals(500.0, cart.calculateTotal());
    }

    @Test
    void testProcessPayment() {
        Cart cart = new Cart();
        Product product = new Product(1, "Test Product", 100.0, 10);
        cart.addItem(product, 5);
        cart.processPayment(500.0);
        assertEquals(0, cart.processPayment(500));
    }

    @Test
    void testGenerateReceipt() {
        Cart cart = new Cart();
        Product product = new Product(1, "Test Product", 100.0, 10);
        cart.addItem(product, 5);
        cart.processPayment(500.0);
        cart.generateReceipt(); // manually verify receipt output
    }


}
