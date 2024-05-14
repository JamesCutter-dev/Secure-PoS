// this test panel is a simple form that allows the user to add a product to the system, it is a basic prototype

package SecureEpos;
import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel{
    private JTextField nameField, priceField, quantityField;
    private JButton addButton;

    public ProductPanel() {
        setBorder(BorderFactory.createTitledBorder("Add Product"));
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField(10);
        add(nameField);

        add(new JLabel("Price:"));
        priceField = new JTextField(10);
        add(priceField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField(10);
        add(quantityField);

        addButton = new JButton("Add");
        add(addButton);
        addButton.addActionListener(e -> addProduct());

    }
    private void addProduct() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        // integrate with product manager
        System.out.println("Added product: " + name + " Price: " + price + " Quantity: " + quantity);
    }
}
