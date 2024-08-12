package xnscsh;

public class UserLoginState {
    // Private static instance of the same class
    private static UserLoginState instance;
    private boolean loggedIn;

    // Private constructor to restrict instantiation from other classes
    private UserLoginState() {
        loggedIn = false;
    }

    // Public method to provide global access point
    public static UserLoginState getInstance() {
        if (instance == null) {
            instance = new UserLoginState();
        }
        return instance;
    }

    // Method to log in the user
    public void login() {
        loggedIn = true;
        System.out.println("User logged in.");
    }

    // Method to log out the user
    public void logout() {
        loggedIn = false;
        System.out.println("User logged out.");
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        return loggedIn;
    }

    // Method to view balance
    public void viewBalance() {
        if (loggedIn) {
            System.out.println("Viewing balance...");
        } else {
            System.out.println("Please log in to view balance.");
        }
    }

    // Method to deposit money
    public void deposit() {
        if (loggedIn) {
            System.out.println("Depositing money...");
        } else {
            System.out.println("Please log in to deposit money.");
        }
    }

    // Method to withdraw money
    public void withdraw() {
        if (loggedIn) {
            System.out.println("Withdrawing money...");
        } else {
            System.out.println("Please log in to withdraw money.");
        }
    }

    public static void main(String[] args) {
        UserLoginState userLoginState = UserLoginState.getInstance();

        // Trying to view balance without logging in
        userLoginState.viewBalance();

        // Logging in the user
        userLoginState.login();

        // Performing operations
        userLoginState.viewBalance();
        userLoginState.deposit();
        userLoginState.withdraw();

        // Logging out the user
        userLoginState.logout();

        // Trying to perform operations after logging out
        userLoginState.viewBalance();
    }
}