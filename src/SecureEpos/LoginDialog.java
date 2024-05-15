package SecureEpos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private boolean authenticated = false;

    public LoginDialog(Frame parent) {
        super(parent, "Login", true);
        setupUI();
        setLocationRelativeTo(parent);
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> checkCredentials());
        panel.add(loginButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> System.exit(0));
        panel.add(cancelButton);

        setContentPane(panel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void checkCredentials() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if ("admin".equals(username) && "password".equals(password)) {
            authenticated = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}