package com.expensetracker.util;

import java.io.FileWriter;
import java.io.IOException;

import com.expensetracker.model.Expense;

public class FileUtil {

    private static final String FILE_NAME = "expenses.txt";

    public static void saveExpense(Expense expense) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(expense.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

