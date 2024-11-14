package persistence;

import core.Expense;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

    // Establishes a connection to the MySQL database
    public Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/expensetracker";
        String user = "root";
        String password = "Bharani@2006";
        return DriverManager.getConnection(url, user, password);
    }

    // Inserts an Expense object into the expenses table in the database
    public void addExpenseToDB(Expense expense) {
        String sql = "INSERT INTO expenses (description, amount, category, date) VALUES (?, ?, ?, ?)";
        
        // Try-with-resources to auto-close connection and statement
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, expense.getDescription());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setDate(4, Date.valueOf(expense.getDate())); // Convert LocalDate to SQL Date
            pstmt.executeUpdate();
            System.out.println("Expense saved to database successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving expense to database: " + e.getMessage());
        }
    }
}
