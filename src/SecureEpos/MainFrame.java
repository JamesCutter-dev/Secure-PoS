// This GUI will handle the main frame of the application, it contains a list of options
package SecureEpos;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private ProductManager productManager = new ProductManager();
    private Cart cart = new Cart();

    public MainFrame() {
        setTitle("EPoS System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupMenu();
        setupPanels();
        add(cardPanel);

        setVisible(true);
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem addProductItem = new JMenuItem("Add Product");
        addProductItem.addActionListener(e -> cardLayout.show(cardPanel, "Add Product"));
        menu.add(addProductItem);

        JMenuItem updateProductItem = new JMenuItem("Update Product");
        updateProductItem.addActionListener(e -> {
            ((UpdateProductPanel) cardPanel.getComponent(2)).refreshProductDropdown();
            cardLayout.show(cardPanel, "Update Product");
        });
        menu.add(updateProductItem);

        JMenuItem removeProductItem = new JMenuItem("Remove Product");
        removeProductItem.addActionListener(e -> new RemoveProductDialog(this, productManager));
        menu.add(removeProductItem);

        JMenuItem viewProductsItem = new JMenuItem("View Products");
        viewProductsItem.addActionListener(e -> {
            ((ProductPanel) cardPanel.getComponent(1)).refreshTable();
            cardLayout.show(cardPanel, "View Products");
        });
        menu.add(viewProductsItem);

        JMenuItem addItemToCart = new JMenuItem("Add Item to Cart");
        addItemToCart.addActionListener(e -> {
            ((AddItemToCartPanel) cardPanel.getComponent(3)).refreshProducts();  // Adjust index accordingly
            cardLayout.show(cardPanel, "Add Item to Cart");
        });
        menu.add(addItemToCart);

        // JMenuItem processPaymentItem = new JMenuItem("Process Payment");
        // processPaymentItem.addActionListener(e -> new ProcessPaymentDialog(this, cart));
        // menu.add(processPaymentItem);

        JMenuItem viewCart = new JMenuItem("View Cart");
        viewCart.addActionListener(e -> {
            ((CartPanel) cardPanel.getComponent(4)).refreshCart();
            cardLayout.show(cardPanel, "Cart");
        });
        menu.add(viewCart);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void setupPanels() {
        cardPanel.add(new AddProductPanel(productManager), "Add Product");
        cardPanel.add(new ProductPanel(productManager), "View Products");
        cardPanel.add(new UpdateProductPanel(productManager), "Update Product");
        cardPanel.add(new AddItemToCartPanel(productManager, cart), "Add Item to Cart");
        cardPanel.add(new CartPanel(cart, productManager), "Cart");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
