package SecureEpos;

import javax.swing.*;
import java.awt.*;
// This was superceded
/*
public class ProcessPaymentDialog extends JDialog{
    private JTextField paymentField;
    private JButton processButton;
    private JLabel changeLabel;
    private Cart cart;
    public ProcessPaymentDialog(JFrame parent, Cart cart) {
        super(parent, "Process Payment", true);
        this.cart = cart;
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Payment Amount:"));
        paymentField = new JTextField();
        add(paymentField);

        processButton = new JButton("Process Payment");
        processButton.addActionListener(e -> processPayment());
        add(processButton);

        changeLabel = new JLabel("Change Due: -");
        add(changeLabel);

        setLocationRelativeTo(parent);
        setVisible(true);
    }
    private void processPayment() {
        try {
            double amountPaid = Double.parseDouble(paymentField.getText());
            double change = cart.processPayment(amountPaid);
            if (change >= 0) {
                changeLabel.setText("Change Due: " + change);
                JOptionPane.showMessageDialog(this, "Payment processed. Change: " + change);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient payment.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid payment amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
*/