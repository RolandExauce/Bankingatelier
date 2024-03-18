import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // for storing users, though users are lost after programm break, best would be
    // a database
    private static Map<String, User> users = new HashMap<>(); //Map implements hashMap

    public static void main(String[] args) {

        // initialze scanner to get input
        Scanner scanner = new Scanner(System.in);

        // some welcome texts
        System.out.println("Welcome to Banking Orlando!");
        System.out.println("Press 'a' to login or 'b' to register.");

        char option;
        do {
            System.out.print("Enter your choice: ");
            option = scanner.nextLine().charAt(0);

            if (option != 'a' && option != 'b') {
                System.out.println("Invalid option. Please try again.");
            }
        } while (option != 'a' && option != 'b'); // keep displaying until one of the options is chosen

        if (option == 'a') {
            loginUser(scanner);
        } else if (option == 'b') {
            registerUser(scanner);
        }
        scanner.close(); //close the scanner
    }

    // register user method
    private static void registerUser(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();


        //create a new user 
        User newUser = new User(firstName, lastName, username, password, 0);
        users.put(username, newUser); //store in map

        System.out.println("user registered, now login!");
        loginUser(scanner);
    }

    // login method
    private static void loginUser(Scanner scanner) {
        System.out.print("Enter your username (or 'r' to return to main menu): ");
        String username = scanner.nextLine();

        
        if (username.equals("r")) {
            main(new String[0]);// restart main method to return to main menu
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // get user by provided username and compare passwords
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getFirstName() + " " + user.getLastName());
            System.out.println("Current balance: " + user.getBalance() + " EUR");

            // Options for withdraw or deposit
            System.out.println("Press 'c' to withdraw or 'd' to deposit.");

            char option;
            do {
                System.out.print("Enter your choice: ");
                option = scanner.nextLine().charAt(0);
                if (option != 'c' && option != 'd') {
                    System.out.println("Invalid option. Please try again.");
                }
            } while (option != 'c' && option != 'd');

            if (option == 'c') {
                withdrawMoney(scanner, user);
            } else if (option == 'd') {
                depositMoney(scanner, user);
            }

        } else {
            System.out.println("Invalid username or password. Please try again or type 'r' to return to main menu.");
            loginUser(scanner);
        }

    }

    // Withdraw money
    private static void withdrawMoney(Scanner scanner, User user) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        user.withdraw(amount);
    }

    // Deposit money
    private static void depositMoney(Scanner scanner, User user) {
        System.out.print("Enter the amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        user.deposit(amount);
    }

}
