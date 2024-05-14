package SecureEpos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CartPanel extends JPanel { // This panel displays the cart in a table
    private Cart cart;
    private ProductManager productManager;
    private JTable table;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;
    private JButton addButton, checkoutButton, applyDiscountButton, processPaymentButton;
    private JTextField discountField, paymentField;
    private JLabel totalAmountDueLabel;


    public CartPanel(Cart cart, ProductManager productManager) {
        this.cart = cart;
        this.productManager = productManager;
        setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(350); // You can adjust this value to suit your layout

        JPanel addItemPanel = setupAddItemPanel();
        JPanel viewCartPanel = setupViewCartPanel();

        splitPane.setLeftComponent(addItemPanel);
        splitPane.setRightComponent(viewCartPanel);

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel setupAddItemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // Simple grid layout

        productComboBox = new JComboBox<>();
        productManager.getProducts().forEach(productComboBox::addItem);
        panel.add(new JLabel("Select Product:"));
        panel.add(productComboBox);

        quantityField = new JTextField();
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> addItemToCart());
        panel.add(addButton);

        return panel;
    }

    private void addItemToCart() {
        Product selectedProduct = (Product) productComboBox.getSelectedItem();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText());
            if (quantity > 0 && selectedProduct != null) {
                cart.addItem(selectedProduct, quantity);
                refreshCart();
                updateTotalAmountDue();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel setupViewCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        Vector<String> columnNames = getColumnNames();
        table = new JTable(new DefaultTableModel(null, columnNames));

        totalAmountDueLabel = new JLabel("Total Amount Due: £0.00");

        JPanel bottomPanel = setupBottomPanel();
        bottomPanel.add(totalAmountDueLabel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        refreshCart(); // Initial refresh to populate table
        return panel;
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
        bottomPanel.add(totalAmountDueLabel);

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

    private void setupProductDropdown() {
        productComboBox = new JComboBox<>();
        refreshProductDropdown();
    }

    public void refreshProductDropdown() {
        productComboBox.removeAllItems();
        for (Product product : productManager.getProducts()) {
            productComboBox.addItem(product);
        }
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