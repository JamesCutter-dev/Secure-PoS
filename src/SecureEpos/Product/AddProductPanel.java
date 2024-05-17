package SecureEpos.Product;

import SecureEpos.Product.Product;
import SecureEpos.Product.ProductManager;

import javax.swing.*;
import java.awt.*;

public class AddProductPanel extends JPanel {
    private JTextField idField, nameField, priceField, quantityField, discountField;
    private JButton addButton;

    public AddProductPanel(ProductManager productManager) {
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Product ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Discount:"));
        discountField = new JTextField();
        add(discountField);

        addButton = new JButton("Add Product");
        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                double discount = Double.parseDouble(discountField.getText());

                Product product = new Product(id, name, price, quantity);
                product.setDiscount(discount);
                productManager.addProduct(product);
                JOptionPane.showMessageDialog(this, "Product added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please check your inputs. All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(addButton);
    }
}