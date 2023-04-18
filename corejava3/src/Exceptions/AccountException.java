package Exceptions;

public class AccountException extends Exception {
    public AccountException(InsufficientFundsException cause) {
        super(cause);
    }
}
