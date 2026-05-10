package ATM;

public enum ATMState {
    START,
    PIN_ENTRY,
    AUTHENTICATED,
    MENU,
    WITHDRAW,
    DEPOSIT,
    CHECK_BALANCE,
    EXIT,
    BLOCKED
}