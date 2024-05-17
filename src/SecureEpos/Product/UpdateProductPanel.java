package SecureEpos.Product;

import javax.swing.*;
import java.awt.*;

public class UpdateProductPanel extends JPanel {
    private JComboBox<Product> productDropdown;
    private JTextField nameField, priceField, quantityField, discountField;
    private JButton updateButton;
    private ProductManager productManager;

    public UpdateProductPanel(ProductManager productManager) {
        this.productManager = productManager;
        setLayout(new GridLayout(6, 2, 5, 5));

        productDropdown = new JComboBox<>();
        refreshProductDropdown();  // Populate JComboBox with initial data
        productDropdown.addActionListener(e -> {
            Product selectedProduct = (Product) productDropdown.getSelectedItem();
            populateFields(selectedProduct);
        });
        productManager.getProducts().forEach(productDropdown::addItem);
        productDropdown.addActionListener(e -> populateFields((Product) productDropdown.getSelectedItem()));
        add(new JLabel("Select Product:"));
        add(productDropdown);

        nameField = new JTextField();
        add(new JLabel("Name:"));
        add(nameField);

        priceField = new JTextField();
        add(new JLabel("Price:"));
        add(priceField);

        quantityField = new JTextField();
        add(new JLabel("Quantity:"));
        add(quantityField);

        discountField = new JTextField();
        add(new JLabel("Discount:"));
        add(discountField);

        updateButton = new JButton("Update Product");
        updateButton.addActionListener(e -> updateProduct());
        add(updateButton);
    }

    private void populateFields(Product product) {
        if (product != null) {
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            quantityField.setText(String.valueOf(product.getQuantity()));
            discountField.setText(String.valueOf(product.getDiscount()));
        }
    }

    public void refreshProductDropdown() {
        productDropdown.removeAllItems();
        productManager.getProducts().forEach(productDropdown::addItem);
    }

    private void updateProduct() {
        Product selectedProduct = (Product) productDropdown.getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setName(nameField.getText());
            selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
            selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedProduct.setDiscount(Double.parseDouble(discountField.getText()));
            JOptionPane.showMessageDialog(this, "Product updated successfully!");
        }
    }
}