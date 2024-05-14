/*
package SecureEpos;

import javax.swing.*;
import java.awt.*;

public class AddItemToCartPanel extends JPanel {
    private JComboBox<Product> productDropdown;
    private JTextField quantityField;
    private JButton addButton;
    private ProductManager productManager;
    private Cart cart;

    public AddItemToCartPanel(ProductManager productManager, Cart cart) {
        this.productManager = productManager;
        this.cart = cart;
        setLayout(new GridLayout (0,1));

        productDropdown = new JComboBox<>();
        refreshProducts();
        productManager.getProducts().forEach(productDropdown::addItem);

        add(new JLabel("Select Product:"));
        add(productDropdown);

        quantityField = new JTextField();
        add(new JLabel("Quantity:"));
        add(quantityField);

        addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> addItem());
        add(addButton);
    }

    private void addItem() {
        Product selectedProduct = (Product) productDropdown.getSelectedItem();
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity > 0 && quantity <= selectedProduct.getQuantity()) {
                cart.addItem(selectedProduct, quantity);
                selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
                JOptionPane.showMessageDialog(this, "Item added to cart.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshProducts() {
        productDropdown.removeAllItems();
        productManager.getProducts().forEach(productDropdown::addItem);
    }
}
*/