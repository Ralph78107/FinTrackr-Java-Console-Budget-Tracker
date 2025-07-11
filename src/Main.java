import models.Expense;
import services.ExpenseManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- FinTrackr ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Filter by Category");
            System.out.println("4. Total Spent");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Description: ");
                    String desc = scanner.nextLine();
                    Expense e = new Expense(category, amount, desc, LocalDate.now());
                    manager.addExpense(e);
                    System.out.println("Expense added!");
                }
                case 2 -> {
                    List<Expense> allExpenses = manager.getAllExpenses();
                    if (allExpenses.isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {
                        System.out.println("\n--- All Expenses ---");
                        for (Expense e : allExpenses) {
                            System.out.println(e);
                        }
                    }
                    System.out.println("\nPress Enter to return to the menu...");
                    scanner.nextLine(); // This clears the leftover newline
                    scanner.nextLine(); // This waits for actual Enter
                    break;
                    }   
                
                case 3 -> {
                    System.out.print("Category: ");
                    String cat = scanner.nextLine();
                    manager.getExpensesByCategory(cat).forEach(System.out::println);
                }
                case 4 -> System.out.println("Total spent: $" + manager.getTotalSpent());
                case 5 -> {
                    running = false;
                    System.out.println("Exiting. Thank you!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
