package Tests;
import SecureEpos.Product.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product(1, "Test Product", 100.0, 10);
        assertEquals(1, product.getID());
        assertEquals("Test Product", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals(0.0, product.getDiscount());
    }

    @Test
    void testApplyingDiscount() {
        Product product = new Product(1, "Test Product", 100.0, 10);
        product.setDiscount(10.0);
        assertEquals(90.0, product.getPriceAfterDiscount());
    }

    @Test
    void testPriceAfterDiscount() {
        Product product = new Product(1, "Test Product", 100.0, 10);
        product.setDiscount(10.0);
        assertEquals(90.0, product.getPriceAfterDiscount());
    }

}
