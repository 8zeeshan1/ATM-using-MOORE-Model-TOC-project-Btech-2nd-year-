package ATM;

import java.util.HashMap;
import java.util.Scanner;

public class ATMServiceImpl implements ATMService {

    private HashMap<Integer, Card> cards = new HashMap<>();
    private Card currentCard = null;
    private ATMState state = ATMState.START;

    public ATMServiceImpl() {
        // Preloaded cards
        cards.put(1234, new Card(1234, 1111, 5000));
        cards.put(5678, new Card(5678, 2222, 10000));
        cards.put(1042, new Card(1042, 2428, 60000));
        cards.put(2231, new Card(2231, 8899, 15000));
        cards.put(2988, new Card(2988, 2345, 12000));
    }

    public void insertCard(int cardNumber) {
        if (cards.containsKey(cardNumber)) {
            currentCard = cards.get(cardNumber);
            if (!currentCard.isActive()) {
                state = ATMState.BLOCKED;
                System.out.println("Card is blocked!");
            } else {
                state = ATMState.PIN_ENTRY;
                System.out.println("Card accepted. Enter PIN:");
            }
        } else {
            System.out.println("Invalid card!");
        }
    }

    public void enterPin(int pin) {
        if (state != ATMState.PIN_ENTRY) return;

        if (currentCard.validatePin(pin)) {
            state = ATMState.AUTHENTICATED;
            System.out.println("PIN correct!");
            showMenu();
        } else {
            if (!currentCard.isActive()) {
                state = ATMState.BLOCKED;
            } else {
                System.out.println("Wrong PIN. Try again.");
            }
        }
    }

    public void showMenu() {
        state = ATMState.MENU;
        System.out.println("\n1. Withdraw\n2. Deposit\n3. Balance\n4. Exit");
    }

    public void withdraw(double amount) {
        state = ATMState.WITHDRAW;

        if (currentCard.withdraw(amount)) {
            System.out.println("Withdraw successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
        showMenu();
    }

    public void deposit(double amount) {
        state = ATMState.DEPOSIT;
        currentCard.deposit(amount);
        System.out.println("Deposit successful!");
        showMenu();
    }

    public void checkBalance() {
        state = ATMState.CHECK_BALANCE;
        System.out.println("Balance: " + currentCard.getBalance());
        showMenu();
    }

    public void exit() {
        state = ATMState.EXIT;
        System.out.println("Transaction ended.");
    }

    public ATMState getState() {
        return state;
    }
}