package SecureEpos.Product;

import javax.swing.*;
import java.awt.*;

public class RemoveProductDialog extends JDialog {
    private JComboBox<Product> productDropdown;
    private JButton removeButton;
    private ProductManager productManager;

    public RemoveProductDialog(JFrame parent, ProductManager productManager) {
        super(parent, "Remove Product", true);
        this.productManager = productManager;
        setSize(300, 120);
        setLayout(new GridLayout(2, 2));

        productDropdown = new JComboBox<>();
        productManager.getProducts().forEach(productDropdown::addItem);
        add(new JLabel("Select Product:"));
        add(productDropdown);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            Product selectedProduct = (Product) productDropdown.getSelectedItem();
            if (selectedProduct != null) {
                productManager.removeProduct(selectedProduct.getID());
                JOptionPane.showMessageDialog(this, "Product removed successfully!");
                dispose();
            }
        });
        add(removeButton);

        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
