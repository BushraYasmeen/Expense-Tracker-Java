package com.expensetracker.service;

import java.util.ArrayList;
import java.util.List;

import com.expensetracker.model.Expense;
import com.expensetracker.util.FileUtil;

public class ExpenseService {

    private List<Expense> expenseList = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenseList.add(expense);
        FileUtil.saveExpense(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseList;
    }
}

