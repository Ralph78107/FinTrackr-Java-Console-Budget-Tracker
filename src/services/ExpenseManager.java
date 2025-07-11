package services;

import models.Expense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public ExpenseManager() {
        loadExpensesFromFile(); //Load saved expenses
    }

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

private void loadExpensesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String description = parts[3];
                    expenses.add(new Expense(category, amount, description, date));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous expenses found or error reading file.");
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
