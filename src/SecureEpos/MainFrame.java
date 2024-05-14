// This is a simple test of the GUI before implementing all the functions of EposMain

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
        setSize(800, 600);
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

        JMenuItem viewProductsItem = new JMenuItem("View Products");
        viewProductsItem.addActionListener(e -> {
            ((ProductPanel) cardPanel.getComponent(1)).refreshTable();
            cardLayout.show(cardPanel, "View Products");
        });
        menu.add(viewProductsItem);

        JMenuItem viewCart = new JMenuItem("View Cart");
        viewCart.addActionListener(e -> {
            ((CartPanel) cardPanel.getComponent(2)).refreshCart();
            cardLayout.show(cardPanel, "Cart");
        });
        menu.add(viewCart);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void setupPanels() {
        cardPanel.add(new AddProductPanel(productManager), "Add Product");
        cardPanel.add(new ProductPanel(productManager), "View Products");
        cardPanel.add(new CartPanel(cart, productManager), "Cart");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
