package persistence;

import core.Expense;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public void saveExpensesToFile(ArrayList<Expense> expenses, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    public ArrayList<Expense> loadExpensesFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ArrayList<Expense> object = (ArrayList<Expense>) ois.readObject();
			return object;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
