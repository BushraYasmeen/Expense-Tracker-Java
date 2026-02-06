package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExpenseTrackerFrame extends JFrame {

    JTextField amountField, descField;
    JComboBox<String> categoryBox;
    JButton addBtn, deleteBtn;
    JTable table;
    DefaultTableModel model;
    JLabel totalLabel;

    public ExpenseTrackerFrame() {

        setTitle("Expense Tracker");
        setSize(600, 450);
        setLayout(null);

        JLabel heading = new JLabel("Expense Tracker");
        heading.setBounds(230, 10, 200, 30);
        add(heading);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 60, 100, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(100, 60, 120, 25);
        add(amountField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(250, 60, 100, 25);
        add(categoryLabel);

        categoryBox = new JComboBox<>(new String[]{"Food", "Travel", "Shopping", "Other"});
        categoryBox.setBounds(330, 60, 150, 25);
        add(categoryBox);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(30, 100, 100, 25);
        add(descLabel);

        descField = new JTextField();
        descField.setBounds(130, 100, 200, 25);
        add(descField);

        addBtn = new JButton("Add Expense");
        addBtn.setBounds(360, 100, 130, 30);
        add(addBtn);

        deleteBtn = new JButton("Delete Expense");
        deleteBtn.setBounds(230, 140, 150, 30);
        add(deleteBtn);

        model = new DefaultTableModel(
                new String[]{"Amount", "Category", "Description"}, 0);

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 190, 520, 180);
        add(sp);

        totalLabel = new JLabel("Total: ₹0");
        totalLabel.setBounds(30, 380, 200, 25);
        add(totalLabel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
