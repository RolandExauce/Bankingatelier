//user class
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private double balance;

    // Constructor
    public User(String firstName, String lastName, String username, String password, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of " + amount + " EUR successful.");
        } else {
            System.out.println("You must deposit a sum greater than 0 EUR.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("You withdrawed: " + amount + " successfully.");
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

}
