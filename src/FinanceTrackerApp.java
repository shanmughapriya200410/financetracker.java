import java.util.ArrayList;
import java.util.Scanner;
public class FinanceTrackerApp {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static int transactionCounter = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Secure Java Finance Tracker (Enhanced)");

        System.out.print("Register - Enter username: ");
        String regUser = scanner.nextLine();
        System.out.print("Register - Enter password: ");
        String regPass = scanner.nextLine();

        User user = new User(regUser, regPass);

        System.out.println("Please login.");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (user.authenticate(username, password)) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials, try again.");
            }
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Edit Transaction");
            System.out.println("5. Delete Transaction");
            System.out.println("6. Show Summary Dashboard");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addTransaction("income");
                    break;
                case 2:
                    addTransaction("expense");
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    editTransaction();
                    break;
                case 5:
                    deleteTransaction();
                    break;
                case 6:
                    showSummaryDashboard();
                    break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void addTransaction(String type) {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        double amount = 0;
        while (true) {
            System.out.print("Enter amount: ");
            String input = scanner.nextLine();
            try {
                amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.println("Amount must be positive. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount input. Please enter a valid number.");
            }
        }

        Transaction t;
        if (type.equals("income")) {
            t = new Income(transactionCounter++, category, amount);
        } else {
            t = new Expense(transactionCounter++, category, amount);
        }
        transactions.add(t);
        System.out.println(type.substring(0, 1).toUpperCase() + type.substring(1) + " added successfully.");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to show.");
            return;
        }

        System.out.print("Enter category to filter (or press Enter to view all): ");
        String filterCat = scanner.nextLine().trim();

        System.out.println("\nTransactions:");
        for (Transaction t : transactions) {
            if (filterCat.isEmpty() || t.getCategory().equalsIgnoreCase(filterCat)) {
                System.out.println(t);
            }
        }
    }

    private static Transaction findTransactionById(int id) {
        for (Transaction t : transactions) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    private static void editTransaction() {
        System.out.print("Enter Transaction ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        Transaction t = findTransactionById(id);

        if (t == null) {
            System.out.println("Transaction ID not found.");
            return;
        }

        System.out.print("Enter new category (current: " + t.getCategory() + "): ");
        String newCategory = scanner.nextLine();
        if (!newCategory.trim().isEmpty()) {
            t.setCategory(newCategory);
        }

        double newAmount;
        while (true) {
            System.out.print("Enter new amount (current: " + t.getAmount() + "): ");
            String amtStr = scanner.nextLine();
            if (amtStr.trim().isEmpty()) break;
            try {
                newAmount = Double.parseDouble(amtStr);
                if (newAmount > 0) {
                    t.setAmount(newAmount);
                    break;
                } else {
                    System.out.println("Amount must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount, please enter a valid number.");
            }
        }
        System.out.println("Transaction updated.");
    }

    private static void deleteTransaction() {
        System.out.print("Enter Transaction ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Transaction t = findTransactionById(id);

        if (t == null) {
            System.out.println("Transaction ID not found.");
            return;
        }
        transactions.remove(t);
        System.out.println("Transaction deleted.");
    }

    private static void showSummaryDashboard() {
        double totalIncome = 0, totalExpense = 0;
        for (Transaction t : transactions) {
            if (t instanceof Income) totalIncome += t.getAmount();
            else totalExpense += t.getAmount();
        }
        double balance = totalIncome - totalExpense;

        System.out.println("\n======= Summary Dashboard =======");
        System.out.printf("Total Income: %.2f\n", totalIncome);
        System.out.printf("Total Expense: %.2f\n", totalExpense);
        System.out.printf("Net Balance: %.2f\n", balance);
        System.out.println("=================================");
    }
}