package at.htlleonding.bank;

import java.util.Arrays;
import java.util.Locale;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class AccountTest {

    private Account instance;

    public AccountTest() {
        Locale.setDefault(Locale.ENGLISH);
    }

    public static Account[] createAccountBooks() {
        Account[] books = new Account[]{
                new Account(4711),
                new Account(815),
                new Account(42),
                new Account(47),
                new Account(11)
        };

        books[0].depositAmount(75.0);
        books[0].setInterestRate(0.25);

        books[1].depositAmount(700.25);
        books[1].setInterestRate(0.75);

        books[2].depositAmount(7238.0);
        books[2].setInterestRate(1.49);

        books[3].depositAmount(325.0);
        books[3].setInterestRate(0.03);

        books[4].depositAmount(321);
        books[4].setInterestRate(0.98);

        return books;
    }

    @BeforeEach
    public void setUp() {
        instance = new Account(4711);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void t01_testCreationOfDefaultAccount() {
        assertEquals(4711, instance.getAccountNumber());
        assertEquals(Account.DEFAULT_INTEREST_RATE, instance.getInterestRate(), 0.);
        assertEquals(Account.DEFAULT_MAX_OVERDRAFT, instance.getMaxOverdraft(), 0.);
    }

    @Test
    public void t02_testCreationOfAccountWithSpecialInterestRate() {
        Account accountBook = new Account(12345, .95);
        assertEquals(.95, accountBook.getInterestRate(), 0.);
        assertEquals(Account.DEFAULT_MAX_OVERDRAFT, accountBook.getMaxOverdraft(), 0.);
    }

    @Test
    public void t03_testSetMaxOverdraft() {
        assertEquals(Account.DEFAULT_MAX_OVERDRAFT, instance.getMaxOverdraft(), 0.);
        instance.setMaxOverdraft(-500.);
        assertEquals(-500., instance.getMaxOverdraft(), 0.0);
    }

    @Test
    public void t04_testSetMaxOverdraftWithPositiveAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setMaxOverdraft(0.01);
        });
    }

    @Test
    public void t05_testDepositAmount() {
        instance.depositAmount(0.01);
        assertEquals(0.01, instance.getBalance(), 0.0);
        instance.depositAmount(100);
        assertEquals(100.01, instance.getBalance(), 0.0);
    }

    @Test
    public void t06_testDepositNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.depositAmount(-100);
        });
    }

    @Test
    public void t07_testWithdrawAmount() {
        instance.withdrawAmount(100.);
        assertEquals(-100., instance.getBalance(), 0.);
    }

    @Test
    public void t08_testWithdrawNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.withdrawAmount(-0.01);
        });
    }

    @Test
    public void t09_testWithdrawNegativeAmountDoesNotTouchBalance() {
        try {
            instance.withdrawAmount(-.01);
        } catch (IllegalArgumentException e) {
            assertEquals(0., instance.getBalance(), 0.);
        } finally {
            assertEquals(0., instance.getBalance(), 0.);
        }
    }

    @Test
    public void t10_testWithdrawBeyondOverdraft() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.withdrawAmount(instance.getMaxOverdraft() * -1 + 0.0001);
        });
    }

    @Test
    public void t11_testWithdrawBeyondOverdraftDoesNotTouchBalance() {
        try {
            instance.withdrawAmount(instance.getMaxOverdraft() * -1 + 0.0001);
        } catch (IllegalArgumentException e) {
            assertEquals(0., instance.getBalance(), 0.);
        } finally {
            assertEquals(0., instance.getBalance(), 0.);
        }
    }

    @Test
    public void t12_testCompareToNull() {
        assertThrows(NullPointerException.class, () -> {
            instance.compareTo(null);
        });
    }

    @Test
    public void t13_testCompareTo() {
        Account[] books = createAccountBooks();
        Arrays.sort(books);

        int[] accountNumbersSorted = new int[books.length];
        for (int i = 0; i < books.length; i++) {
            accountNumbersSorted[i] = books[i].getAccountNumber();
        }

        assertArrayEquals(new int[]{11, 42, 47, 815, 4711},
                accountNumbersSorted);
    }

    @Test
    public void t14_testComparator() {
        Account[] books = createAccountBooks();

        Arrays.sort(books, new AccountBookAmountComparator());

        int[] accountsSortedByBalance = new int[books.length];
        for (int i = 0; i < books.length; i++) {
            accountsSortedByBalance[i] = books[i].getAccountNumber();
        }

        int[] expectedAccountOrder = new int[]{4711, 11, 47, 815, 42};
        assertArrayEquals(expectedAccountOrder, accountsSortedByBalance);
    }

    @Test
    public void t15_testToString() {
        assertEquals("4711;0.00;1.50;-1000.00", instance.toString());
    }

    @Test
    public void t16_testEquals() {
        Account accountA = new Account(4020);
        Account accountB = new Account(4020, 0.25);
        Account accountC = new Account(1234);

        assertEquals(accountA, accountB);
        assertEquals(accountB, accountA);
        assertNotEquals(accountB, accountC);
        assertNotEquals(accountC, accountA);
    }
}