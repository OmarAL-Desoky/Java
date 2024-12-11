package at.htlleonding.bank;

public class AccountPersistenceException extends RuntimeException{
    public AccountPersistenceException(String message) {
        super(message);
    }

    public AccountPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
