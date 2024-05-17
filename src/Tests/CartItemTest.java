package Tests;

import SecureEpos.Cart.CartItem;
import SecureEpos.Product.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

        @Test
        void testCartItemCreation() {
            Product product = new Product(1, "Test Product", 100.0, 10);
            CartItem cartItem = new CartItem(product, 5);
            assertEquals(product, cartItem.getProduct());
            assertEquals(5, cartItem.getQuantity()); // 5 items in the cart
        }

        @Test
        void testCartItemTotalPrice() {
            Product product = new Product(1, "Test Product", 100.0, 10);
            CartItem cartItem = new CartItem(product, 5);
            assertEquals(500.0, cartItem.getTotalPrice()); // total price calculated
        }

        @Test
        void testCartItemSetQuantity() {
            Product product = new Product(1, "Test Product", 100.0, 10);
            CartItem cartItem = new CartItem(product, 5);
            cartItem.setQuantity(10);
            assertEquals(10, cartItem.getQuantity()); // quantity set to 10
        }

        @Test
        void testCartItemToString() {
            Product product = new Product(1, "Test Product", 100.0, 10);
            CartItem cartItem = new CartItem(product, 5);
            assertEquals("Product: Test Product, Quantity: 5, Total Price: 500.0", cartItem.toString()); // correct string representation
        }
}
