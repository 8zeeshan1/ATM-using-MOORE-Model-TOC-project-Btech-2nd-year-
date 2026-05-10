package ATM;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ATMServiceImpl atm = new ATMServiceImpl();

        System.out.println("=== ATM MACHINE STARTED ===");

        while (true) {

            System.out.print("Enter Card Number: ");
            int cardNumber = sc.nextInt();
            atm.insertCard(cardNumber);

            while (atm.getState() == ATMState.PIN_ENTRY) {
                System.out.print("Enter PIN: ");
                int pin = sc.nextInt();
                atm.enterPin(pin);

                if (atm.getState() == ATMState.BLOCKED) break;
            }

            while (atm.getState() == ATMState.MENU) {
                System.out.print("Choose option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount: ");
                        atm.withdraw(sc.nextDouble());
                        break;

                    case 2:
                        System.out.print("Enter amount: ");
                        atm.deposit(sc.nextDouble());
                        break;

                    case 3:
                        atm.checkBalance();
                        break;

                    case 4:
                        atm.exit();
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            }

            if (atm.getState() == ATMState.EXIT || atm.getState() == ATMState.BLOCKED) {
                System.out.println("Session ended.\n");
            }
        }
    }
}
