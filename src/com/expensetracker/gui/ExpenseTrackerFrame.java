package com.expensetracker.gui;

import javax.swing.*;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseTrackerFrame extends JFrame {

    JTextField titleField, amountField, categoryField, dateField;
    JButton addButton;

    ExpenseService service = new ExpenseService();

    public ExpenseTrackerFrame() {

        setTitle("Expense Tracker");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();
                String date = dateField.getText();

                Expense expense = new Expense(title, amount, category, date);
                service.addExpense(expense);

                JOptionPane.showMessageDialog(null, "Expense Added!");
            }
        });

        setVisible(true);
    }
}

