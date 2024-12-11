package at.htlleonding.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class AccountRepositoryTest {
    public static final String FILE_PATH_ORIGINAL = "data/accounts_original.csv";
    public static final String PATH_FILE_WRITTEN = "data/accounts_written.csv";
    public static final String FILE_PATH_CORRUPT = "data/accounts_corrupt.csv";

    @BeforeEach
    public void setUp() throws IOException {
        Path path = Paths.get(PATH_FILE_WRITTEN);
        if(Files.exists(path)) {
            Files.delete(path);
        }
    }

    public AccountRepositoryTest() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void t01_testSingleton() {
        AccountRepository a = AccountRepository.getInstance();
        AccountRepository b = AccountRepository.getInstance();
        assertEquals(true, a == b);
    }

    @Test
    public void t02_testAddSameAccountTwice() {
        Account account = new Account(4020, 0.25);
        Account accountSameNr = new Account(4020, 0.75);

        assertTrue(AccountRepository.getInstance().addAccount(account));
        assertFalse(AccountRepository.getInstance().addAccount(account));
        assertFalse(AccountRepository.getInstance().addAccount(accountSameNr));
    }

    @Test
    public void t03_testGetAccounts() {
        Account account = new Account(4020, 0.25);
        Account anotherAccount = new Account(123);
        Account yetAnotherAccount = new YouthAccount(42, 14);

        AccountRepository.getInstance().addAccount(account);
        AccountRepository.getInstance().addAccount(anotherAccount);
        AccountRepository.getInstance().addAccount(yetAnotherAccount);

        assertEquals(anotherAccount, AccountRepository.getInstance().getAccount(123));
        assertEquals(yetAnotherAccount, AccountRepository.getInstance().getAccount(42));
        assertEquals(account, AccountRepository.getInstance().getAccount(4020));

        assertNull(AccountRepository.getInstance().getAccount(1234));
        assertNull(AccountRepository.getInstance().getAccount(101));
        assertNull(AccountRepository.getInstance().getAccount(5));
    }

    @Test
    public void t04_testLoadFileNotExists() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            AccountRepository.getInstance().loadAccounts("data/doesnt_exist.csv");
        });

        assertEquals("Could not read from accounts file.", ex.getMessage());
        assertEquals(NoSuchFileException.class, ex.getCause().getClass());
    }

    @Test
    public void t05_testLoadCorruptFiles() {
        AccountPersistenceException ex = assertThrows(AccountPersistenceException.class, () -> {
            AccountRepository.getInstance().loadAccounts(FILE_PATH_CORRUPT);
        });

        assertEquals("Account specification is invalid.", ex.getMessage());
        assertEquals(NumberFormatException.class, ex.getCause().getClass());
    }

    @Test
    public void t06_testLoadExistingAccounts() throws IOException {
        AccountRepository.getInstance().loadAccounts(FILE_PATH_ORIGINAL);

        Account accountFromFile = AccountRepository.getInstance().getAccount(815);
        assertEquals(815, accountFromFile.getAccountNumber());
        assertEquals(700.25, accountFromFile.getBalance(), 0.0001);
        assertEquals(0.75, accountFromFile.getInterestRate(), 0.0001);
        assertEquals(-700.0, accountFromFile.getMaxOverdraft(), 0.0001);

        YouthAccount youthAccountFromFile = (YouthAccount) AccountRepository.getInstance().getAccount(47);
        assertEquals(47, youthAccountFromFile.getAccountNumber());
        assertEquals(425.0, youthAccountFromFile.getBalance(), 0.0001);
        assertEquals(0.03, youthAccountFromFile.getInterestRate(), 0.0001);
        assertEquals(0, youthAccountFromFile.getMaxOverdraft(), 0.0001);
        assertEquals(17, youthAccountFromFile.getAge());
    }

    @Test
    public void t07_testWriteAccounts() throws IOException {
        Account account = new Account(4020, 0.25);
        Account anotherAccount = new Account(123);
        Account yetAnotherAccount = new YouthAccount(42, 14);

        AccountRepository.getInstance().addAccount(account);
        AccountRepository.getInstance().addAccount(anotherAccount);
        AccountRepository.getInstance().addAccount(yetAnotherAccount);

        AccountRepository.getInstance().writeAccounts(PATH_FILE_WRITTEN);

        List<String> linesWritten = Files.readAllLines(Paths.get(PATH_FILE_WRITTEN));
        assertEquals("4020;0.00;0.25;-1000.00", linesWritten.get(0));
        assertEquals("123;0.00;1.50;-1000.00", linesWritten.get(1));
        assertEquals("42;20.00;1.50;0.00;14", linesWritten.get(2));
    }
}
