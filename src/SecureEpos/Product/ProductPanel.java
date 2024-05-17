// This panel displays products in a table

package SecureEpos.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class ProductPanel extends JPanel {
    private JTable table;
    private ProductManager productManager;

    public ProductPanel(ProductManager productManager) {
        this.productManager = productManager;
        setLayout(new BorderLayout());

        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Price");
        columnNames.add("Quantity");
        columnNames.add("Discount");

        table = new JTable(new Vector<>(), columnNames);
        refreshTable();

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refreshTable() {
        Vector<Vector<Object>> data = new Vector<>();
        for (Product product : productManager.getProducts()) {
            Vector<Object> row = new Vector<>();
            row.add(product.getID());
            row.add(product.getName());
            row.add(product.getPrice());
            row.add(product.getQuantity());
            row.add(product.getDiscount());
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
