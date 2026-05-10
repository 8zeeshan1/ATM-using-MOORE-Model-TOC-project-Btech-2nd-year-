package ATM;

public class Card {
    private int cardNumber;
    private int pin;
    private boolean isActive;
    private double balance;
    private int attempts;

    public Card(int cardNumber, int pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.isActive = true;
        this.attempts = 0;
    }

    public int getCardNumber() { return cardNumber; }
    public boolean isActive() { return isActive; }
    public double getBalance() { return balance; }

    public boolean validatePin(int inputPin) {
        if (!isActive) return false;

        if (this.pin == inputPin) {
            attempts = 0;
            return true;
        } else {
            attempts++;
            if (attempts >= 3) {
                isActive = false;
                System.out.println("Card blocked due to 3 wrong attempts!");
            }
            return false;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
