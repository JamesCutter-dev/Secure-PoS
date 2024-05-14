// This is a simple test of the GUI before implementing all the functions of EposMain

package SecureEpos;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;

public class MainFrame extends JFrame{
    private ProductPanel productPanel;
    public MainFrame(String title)  {
        super(title);

        // set layout manager
        setLayout(new BorderLayout());

        // menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem addProductItem = new JMenuItem("Add Product");
        menu.add(addProductItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // panels
        productPanel = new ProductPanel();
        add(productPanel, BorderLayout.CENTER);

        // Menu Actions
        addProductItem.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(productPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        // close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("EPoS System"));
    }
}
