package service;

import core.Expense;
import exception.InvalidExpenseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseTrackerService {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private HashMap<String, List<Expense>> categorizedExpenses = new HashMap<>();

    public void addExpense(String description, double amount, String category, LocalDate date) throws InvalidExpenseException {
        if (amount < 0) {
            throw new InvalidExpenseException("Amount cannot be negative.");
        }
        Expense expense = new Expense(description, amount, category, date);
        expenses.add(expense);
        categorizedExpenses.computeIfAbsent(category, k -> new ArrayList<>()).add(expense);
    }

    public List<Expense> viewExpensesByCategory(String category) {
        return categorizedExpenses.getOrDefault(category, new ArrayList<>());
    }

    public ArrayList<Expense> getAllExpenses() {
        return expenses;
    }
}
