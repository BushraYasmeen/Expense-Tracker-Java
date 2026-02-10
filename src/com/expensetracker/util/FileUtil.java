package com.expensetracker.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.expensetracker.model.Expense;

public class FileUtil {

    private static final String FILE_NAME = "expenses.txt";

    // ✅ Already Existing (Keep As It Is)
    public static void saveExpense(Expense expense) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(expense.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ NEW METHOD (For Table Display)
    public static List<Expense> getAllExpenses() {

        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 4) {
                    Expense expense = new Expense(
                            data[0],
                            Double.parseDouble(data[1]),
                            data[2],
                            data[3]
                    );

                    expenses.add(expense);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return expenses;
    }
}


