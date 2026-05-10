package ATM;

public interface ATMService {
    void insertCard(int cardNumber);
    void enterPin(int pin);
    void showMenu();
    void withdraw(double amount);
    void deposit(double amount);
    void checkBalance();
    void exit();
}