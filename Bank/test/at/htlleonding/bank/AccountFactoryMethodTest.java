package at.htlleonding.bank;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountFactoryMethodTest {
    public AccountFactoryMethodTest() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void testCreateAccountFromString() {
        Account accountBook = Account.createFromString("4713;2500.05;0.75;-1500.00");
        assertEquals(4713, accountBook.getAccountNumber());
        assertEquals(2500.05, accountBook.getBalance(), 0.00);
        assertEquals(0.75, accountBook.getInterestRate(), 0.00);
        assertEquals(-1500., accountBook.getMaxOverdraft(), 0.00);
    }

    @Test
    public void testCreateAccountFromStringWithSpaces() {
        Account accountBook = Account.createFromString("  4713  ; 2500.05;   0.75   ; -1500.00         ");
        assertEquals(4713, accountBook.getAccountNumber());
        assertEquals(2500.05, accountBook.getBalance(), 0.00);
        assertEquals(0.75, accountBook.getInterestRate(), 0.00);
        assertEquals(-1500., accountBook.getMaxOverdraft(), 0.00);
    }

    @Test
    public void testCreateAccountWithDebtFromString() {
        Account accountBook = Account.createFromString("4713;-799.99;0.75;-1500.00");
        assertEquals(4713, accountBook.getAccountNumber());
        assertEquals(-799.99, accountBook.getBalance(), 0.00);
        assertEquals(0.75, accountBook.getInterestRate(), 0.00);
        assertEquals(-1500., accountBook.getMaxOverdraft(), 0.00);
    }

    @Test
    public void testCreateAccountWithTooMuchDebtFromString() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;-799.99;0.75;-500.00");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
    }

    @Test
    public void testCreateYouthAccountFromString() {
        YouthAccount accountBook = (YouthAccount)Account.createFromString("4713;2500.05;0.75;0.00;16");
        assertEquals(4713, accountBook.getAccountNumber());
        assertEquals(2500.05, accountBook.getBalance(), 0.00);
        assertEquals(0.75, accountBook.getInterestRate(), 0.00);
        assertEquals(0., accountBook.getMaxOverdraft(), 0.00);
        assertEquals(16, accountBook.getAge());
    }

    @Test
    public void testCreateYouthAccountFromStringWithSpaces() {
        YouthAccount accountBook = (YouthAccount)Account.createFromString(" 4713      ;      2500.05 ;   0.75   ;  0.00 ;    16 ");
        assertEquals(4713, accountBook.getAccountNumber());
        assertEquals(2500.05, accountBook.getBalance(), 0.00);
        assertEquals(0.75, accountBook.getInterestRate(), 0.00);
        assertEquals(0., accountBook.getMaxOverdraft(), 0.00);
        assertEquals(16, accountBook.getAge());
    }

    @Test
    public void testCreateYouthAccountWithDebtFromString() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            YouthAccount accountBook = (YouthAccount)Account.createFromString("4713;-799.99;0.75;0.00;16");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
    }

    @Test
    public void testTooShortSpecificationLine() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;2500.05;0.75");
        });

        assertEquals("Account specification is too long/short.", ex.getMessage());
        assertEquals(null, ex.getCause());
    }

    @Test
    void testTooLongSpecificationLine() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;2500.05;0.75;0.00;16;4020");
        });

        assertEquals("Account specification is too long/short.", ex.getMessage());
        assertEquals(null, ex.getCause());
    }

    @Test
    public void testInvalidAccountSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("AT73;2500.05;0.75;0.00");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(NumberFormatException.class, ex.getCause().getClass());
    }

    @Test
    public void testInvalidBalanceSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4773;2500,05;0.75;0.00");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(NumberFormatException.class, ex.getCause().getClass());
    }

    @Test
    public void testInvalidInterestRateSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("8473;2500.05;0,75;0.00");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(NumberFormatException.class, ex.getCause().getClass());
    }

    @Test
    public void testInvalidOverdraftSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("9873;2500.05;0.75;0,00");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(NumberFormatException.class, ex.getCause().getClass());
    }

    @Test
    public void testTooYoungAgeSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;2500.05;0.75;0.00;13");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
    }

    @Test
    public void testTooOldAgeSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;2500.05;0.75;0.00;19");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
    }

    @Test
    public void testPositiveOverdraftSpecification() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;2500.05;0.75;100.00");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
    }

    @Test
    public void testCreateYouthAccountWithOverdraft() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            Account accountBook = Account.createFromString("4713;2500.05;0.75;-50.00;16");
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
    }
}