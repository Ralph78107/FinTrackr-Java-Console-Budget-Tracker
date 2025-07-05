package services;

import models.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense e) {
        expenses.add(e);
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
