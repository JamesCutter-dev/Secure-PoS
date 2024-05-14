package SecureEpos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CartPanel extends JPanel { // This panel displays the cart in a table
    private Cart cart;
    private JTable table;
    private JButton checkoutButton, applyDiscountButton, processPaymentButton;
    private JTextField discountField, paymentField;
    private JLabel totalAmountDueLabel;

    public CartPanel(Cart cart, ProductManager productManager) {
        this.cart = cart;
        setLayout(new BorderLayout());

        Vector<String> columnNames = getColumnNames();
        table = new JTable();

        JPanel bottomPanel = setupBottomPanel();

        totalAmountDueLabel = new JLabel();
        updateTotalAmountDue();  // Initial update for the label
        bottomPanel.add(totalAmountDueLabel);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshCart();
    }

    private JPanel setupBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        discountField = new JTextField(10);
        applyDiscountButton = new JButton("Apply Discount");
        applyDiscountButton.addActionListener(e -> {
            try {
                double discount = Double.parseDouble(discountField.getText());
                cart.applyDiscount(discount);
                refreshCart();
                updateTotalAmountDue();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Invalid discount format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        paymentField = new JTextField(10);
        processPaymentButton = new JButton("Process Payment");
        processPaymentButton.addActionListener(e -> processPayment());

        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());

        bottomPanel.add(new JLabel("Discount:"));
        bottomPanel.add(discountField);
        bottomPanel.add(applyDiscountButton);
        bottomPanel.add(new JLabel("Payment:"));
        bottomPanel.add(paymentField);
        bottomPanel.add(processPaymentButton);
        bottomPanel.add(checkoutButton);

        return bottomPanel;
    }

    public void refreshCart() {
        Vector<Vector<Object>> data = new Vector<>();
        for (CartItem item : cart.getItems()) {
            Vector<Object> row = new Vector<>();
            row.add(item.getProduct().getID());
            row.add(item.getProduct().getName());
            row.add(formatMoney(item.getProduct().getPrice()));
            row.add(item.getQuantity());
            row.add(formatMoney(item.getQuantity() * item.getProduct().getPrice()));
            data.add(row);
        }
        DefaultTableModel model = new DefaultTableModel(data, getColumnNames());
        table.setModel(model);
        table.repaint();
        updateTotalAmountDue();
    }

    private void updateTotalAmountDue() {
        double total = cart.calculateTotal();
        totalAmountDueLabel.setText("Total Amount Due: " + formatMoney(total));
    }

    private String formatMoney(double amount) {
        return String.format("£%.2f", amount);
    }

    private Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Price");
        columnNames.add("Quantity");
        columnNames.add("Total Cost");
        return columnNames;
    }

    private void processPayment() {
        try {
            double payment = Double.parseDouble(paymentField.getText());
            double total = cart.calculateTotal(); // Ensure this calculates after discounts
            if (payment < total) {
                JOptionPane.showMessageDialog(this, "Insufficient payment. Please try again.");
            } else {
                double change = cart.processPayment(payment);
                refreshCart();
                JOptionPane.showMessageDialog(this, "Payment successful. Change: " + formatMoney(change));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid payment format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkout() {
        cart.checkout();
        refreshCart();
        JOptionPane.showMessageDialog(this, "Checkout complete. Thank you!");
    }
}