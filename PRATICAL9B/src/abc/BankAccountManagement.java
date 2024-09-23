package abc;
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method to deposit money
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited: %.2f | New Balance: %.2f\n", amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Synchronized method to withdraw money
    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Withdrew: %.2f | New Balance: %.2f\n", amount, balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal of: " + amount);
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Method to get current balance
    public synchronized double getBalance() {
        return balance;
    }
}

class User extends Thread {
    private BankAccount account;

    public User(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        // Randomly perform deposit and withdrawal operations
        for (int i = 0; i < 5; i++) {
            double amount = (Math.random() * 100); // Random amount between 0 and 100

            if (Math.random() < 0.5) {
                account.deposit(amount); // 50% chance to deposit
            } else {
                account.withdraw(amount); // 50% chance to withdraw
            }

            // Pause for a short duration to simulate real-world delay
            try {
                Thread.sleep((long) (Math.random() * 200)); // Sleep for up to 200ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class BankAccountManagement {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.0); // Initial balance of 500.0

        // Create multiple user threads
        User user1 = new User(account);
        User user2 = new User(account);
        User user3 = new User(account);

        // Start the threads
        user1.start();
        user2.start();
        user3.start();

        // Wait for all threads to finish
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Final balance
        System.out.printf("Final Balance: %.2f\n", account.getBalance());
    }
}
