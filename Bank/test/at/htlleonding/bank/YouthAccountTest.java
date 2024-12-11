package at.htlleonding.bank;

import org.junit.jupiter.api.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class YouthAccountTest {

    private YouthAccount instance;

    public YouthAccountTest() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @BeforeEach
    public void setUp() {
        instance = new YouthAccount(4711, 14);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void t01_testConstruction() {
        assertEquals(0., instance.getMaxOverdraft(), 0.);
        assertEquals(20., instance.getBalance(), 0.);
        assertEquals(14, instance.getAge());

        YouthAccount a = new YouthAccount(3333, 18);
        assertEquals(0., a.getMaxOverdraft(), 0.);
        assertEquals(20., a.getBalance(), 0.);
        assertEquals(18, a.getAge());
    }

    @Test
    public void t02_testConstructionForTooYoung() {
        assertThrows(IllegalArgumentException.class, () -> {
            YouthAccount a = new YouthAccount(3333, 13);
        });
    }

    @Test
    public void t03_testConstructionForTooOld() {
        assertThrows(IllegalArgumentException.class, () -> {
            YouthAccount b = new YouthAccount(3333, 19);
        });
    }

    @Test
    public void t04_testSetAge() {
        instance.setAge(15);
        assertEquals(15, instance.getAge());

        instance.setAge(18);
        assertEquals(18, instance.getAge());
    }

    @Test
    public void t05_testSetAgeForTooYoung() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setAge(13);
        });
    }

    @Test
    public void t06_testSetAgeForTooOld() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setAge(19);
        });
    }

    @Test
    public void t07_testSetMaxOverdraft() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.setMaxOverdraft(-0.01);
        });
    }

    @Test
    public void t08_testWithdrawAmount() {
        instance.depositAmount(500);
        instance.withdrawAmount(300);
        assertEquals(220, instance.getBalance(), 0.01);
    }

    @Test
    public void t09_testWithdrawBeyondWithdrawalLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.withdrawAmount(20.01);
        });
    }

    @Test
    public void t10_testWithdrawBeyondLimitDoesNotTouchBalance() {
        try {
            instance.withdrawAmount(20.01);
        } catch (IllegalArgumentException e) {
            assertEquals(20., instance.getBalance(), 0);
        } finally {
            assertEquals(20., instance.getBalance(), 0.);
        }
    }

    @Test
    public void t11_testToString() {
        assertEquals("4711;20.00;1.50;0.00;14", instance.toString());
    }

    @Test
    public void t12_testEquals() {
        YouthAccount accountA = new YouthAccount(42, 15);
        YouthAccount accountB = new YouthAccount(42, 16);
        YouthAccount accountC = new YouthAccount(74, 15);

        assertEquals(accountA, accountB);
        assertEquals(accountB, accountA);
        assertNotEquals(accountB, accountC);
        assertNotEquals(accountC, accountA);
    }
}