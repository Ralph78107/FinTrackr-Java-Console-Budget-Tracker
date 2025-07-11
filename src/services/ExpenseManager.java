package services;

import models.Expense;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense e) {
        expenses.add(e);
        saveExpenseToFile(e);
    }

    private void saveExpenseToFile(Expense e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt", true))) {
            // Format: 2025-07-05, Food, 12.50, Lunch at cafe
            writer.write(String.format("%s,%s,%.2f,%s%n", e.getDate(), e.getCategory(), e.getAmount(), e.getDescription()));
        } catch (IOException ex) {
            System.out.println("Error saving expense: " + ex.getMessage());
        }
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public List<Expense> getExpensesByCategory(String category) {
        return expenses.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public double getTotalSpent() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}
