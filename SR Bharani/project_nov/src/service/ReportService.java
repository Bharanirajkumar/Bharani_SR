package service;

import core.Expense;
import java.time.LocalDate;
import java.util.List;

public class ReportService {

    public void generateMonthlyReport(List<Expense> expenses, int month, int year) {
        System.out.println("Monthly Report for " + month + "/" + year);
        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month && expense.getDate().getYear() == year) {
                System.out.println(expense);
            }
        }
    }
}