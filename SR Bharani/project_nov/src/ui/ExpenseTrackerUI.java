// ui/ExpenseTrackerUI.java
package ui;

import core.Expense;
import exception.InvalidExpenseException;
import service.ExpenseTrackerService;
import service.ReportService;
import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTrackerUI {
    private ExpenseTrackerService trackerService;
    private ReportService reportService;

    public ExpenseTrackerUI() {
        trackerService = new ExpenseTrackerService();
        reportService = new ReportService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses by Category");
            System.out.println("3. Generate Monthly Report");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addExpense(scanner);
                        break;
                    case 2:
                        viewExpensesByCategory(scanner);
                        break;
                    case 3:
                        generateMonthlyReport(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                }
            } catch (InvalidExpenseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addExpense(Scanner scanner) throws InvalidExpenseException {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        trackerService.addExpense(description, amount, category, date);
    }

    private void viewExpensesByCategory(Scanner scanner) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        trackerService.viewExpensesByCategory(category).forEach(System.out::println);
    }

    private void generateMonthlyReport(Scanner scanner) {
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        reportService.generateMonthlyReport(trackerService.getAllExpenses(), month, year);
    }

    public static void main(String[] args) {
        new ExpenseTrackerUI().start();
    }
}
