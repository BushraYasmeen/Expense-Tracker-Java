package com.expensetracker.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.util.FileUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExpenseTrackerFrame extends JFrame {

    JTextField titleField, amountField, categoryField, dateField;
    JButton addButton,deleteButton;
    
    JLabel totalLabel;
    

    JTable table;
    DefaultTableModel tableModel;

    ExpenseService service = new ExpenseService();

    public ExpenseTrackerFrame() {

        setTitle("Expense Tracker");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Labels & Fields =====

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(30, 30, 100, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(150, 30, 150, 25);
        add(titleField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 70, 100, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 70, 150, 25);
        add(amountField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(30, 110, 100, 25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(150, 110, 150, 25);
        add(categoryField);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(30, 150, 100, 25);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(150, 150, 150, 25);
        add(dateField);

        addButton = new JButton("Add Expense");
        addButton.setBounds(120, 200, 150, 30);
        add(addButton);
        
        deleteButton = new JButton("Delete Selected");
        deleteButton.setBounds(300, 200, 150, 30);
        add(deleteButton);


        // ===== Table =====

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Title");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Category");
        tableModel.addColumn("Date");

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 250, 520, 150);
        add(scrollPane);
        
        totalLabel = new JLabel("Total Expense: ₹ 0");
        totalLabel.setBounds(30, 410, 300, 25);
        add(totalLabel);


        // ===== Load Existing Data =====
        loadExpensesToTable();
        updateTotalExpense(); 

        // ===== Button Click =====
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    String title = titleField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    String category = categoryField.getText();
                    String date = dateField.getText();

                    Expense expense = new Expense(title, amount, category, date);
                    service.addExpense(expense);

                    JOptionPane.showMessageDialog(null, "Expense Added!");

                    // Refresh Table
                    loadExpensesToTable();

                    // Clear Fields
                    titleField.setText("");
                    amountField.setText("");
                    categoryField.setText("");
                    dateField.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Enter valid data!");
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!");
                    return;
                }

                FileUtil.deleteExpense(selectedRow);

                loadExpensesToTable();

                JOptionPane.showMessageDialog(null, "Expense Deleted!");
            }
        });


        setVisible(true);
    }

    // ===== Load Data Method =====
    private void loadExpensesToTable() {

        tableModel.setRowCount(0); // Clear old data

        List<Expense> expenses = FileUtil.getAllExpenses();

        for (Expense e : expenses) {
            tableModel.addRow(new Object[]{
                    e.getTitle(),
                    e.getAmount(),
                    e.getCategory(),
                    e.getDate()
            });  
            
        }
        
    }
    private void updateTotalExpense() {

        double total = 0;

        java.util.List<Expense> expenses = FileUtil.getAllExpenses();

        for (Expense e : expenses) {
            total += e.getAmount();
        }

        totalLabel.setText("Total Expense: ₹ " + total);
    }

    
}

