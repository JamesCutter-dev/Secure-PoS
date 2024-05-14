package SecureEpos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CartPanel extends JPanel {
    private Cart cart;
    private JTable table;
    private JButton checkoutButton, applyDiscountButton;
    private JTextField discountField;

    public CartPanel(Cart cart, ProductManager productManager) {
        this.cart = cart;
        setLayout(new BorderLayout());

        Vector<String> columnNames = new Vector<>();
        columnNames.add("Product ID");
        columnNames.add("Name");
        columnNames.add("Price");
        columnNames.add("Quantity");

        table = new JTable(new Vector<>(), columnNames);
        refreshCart();

        JPanel bottomPanel = new JPanel();
        discountField = new JTextField(10);
        applyDiscountButton = new JButton("Apply Discount");
        applyDiscountButton.addActionListener(e -> {
            double discount = Double.parseDouble(discountField.getText());
            cart.applyDiscount(discount);
            refreshCart();
        });
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            cart.checkout();
            refreshCart();
            JOptionPane.showMessageDialog(this, "Checkout complete. Thank you!");
        });

        bottomPanel.add(new JLabel("Discount:"));
        bottomPanel.add(discountField);
        bottomPanel.add(applyDiscountButton);
        bottomPanel.add(checkoutButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshCart() {
        Vector<Vector<Object>> data = new Vector<>();
        for (CartItem item : cart.getItems()) {
            Vector<Object> row = new Vector<>();
            row.add(item.getProduct().getID());
            row.add(item.getProduct().getName());
            row.add(item.getProduct().getPrice());
            row.add(item.getQuantity());
            data.add(row);
        }
        DefaultTableModel model = new DefaultTableModel(data, getColumnNames());
        table.setModel(model);
        table.repaint();
    }

    private Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Price");
        columnNames.add("Quantity");
        columnNames.add("Discount");
        return columnNames;
    }
}