package at.htlleonding.bank;

import java.util.Objects;

public class Account implements Comparable<Account> {
    public static final double DEFAULT_INTEREST_RATE = 1.50;
    public static final double DEFAULT_MAX_OVERDRAFT = -1000;

    private final int accountNumber;
    protected double balance;
    private double interestRate;
    private double maxOverdraft;

    protected double getDefaultMaxOverdraft() {
        return DEFAULT_MAX_OVERDRAFT;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setMaxOverdraft(double maxOverdraft) {
        if(maxOverdraft > 0) {
            throw new IllegalArgumentException();
        }
        this.maxOverdraft = maxOverdraft;
    }

    public double getMaxOverdraft() {
        return maxOverdraft;
    }

    public Account(int accountNumber) {
        this(accountNumber, DEFAULT_INTEREST_RATE);
    }

    public Account(int accountNumber, double interestRate) {
        this.accountNumber = accountNumber;
        setInterestRate(interestRate);
        setMaxOverdraft(getDefaultMaxOverdraft());
    }

    public void withdrawAmount(double amount) {
        if(amount < 0 || balance - amount < maxOverdraft) {
            throw new IllegalArgumentException();
        }

        balance -= amount;
    }

    public void depositAmount(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException();
        }

        balance += amount;
    }

    @Override
    public int compareTo(Account o) {
        return Integer.compare(accountNumber, o.accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }

    @Override
    public String toString() {
        return String.format("%d;%.2f;%.2f;%.2f", accountNumber, balance, interestRate, maxOverdraft);
    }

    public static Account createFromString(String accountString) {
        Account account = null;

        try{
            String[] splittedAccString = accountString.split(";");

            if(splittedAccString.length == 4) {
                account = new Account(Integer.parseInt(splittedAccString[0].strip()));
            }
            else if(splittedAccString.length == 5) {
                account = new YouthAccount(Integer.parseInt(splittedAccString[0].strip()), Integer.parseInt(splittedAccString[4].strip()));
            }
            else{
                throw new AccountPersistenceException("Account specification is too long/short.");
            }

            account.setInterestRate(Double.parseDouble(splittedAccString[2].strip()));
            account.setMaxOverdraft(Double.parseDouble(splittedAccString[3].strip()));

            double balance = Double.parseDouble(splittedAccString[1]);
            balance = account instanceof YouthAccount ? balance - 20 : balance;

            if(balance > 0) {
                account.depositAmount(balance);
            }
            else {
                account.withdrawAmount(Math.abs(balance));
            }
        }
        catch (AccountPersistenceException ex) {
            throw ex;
        }
        catch (RuntimeException ex) {
            throw new AccountPersistenceException("Account specification is invalid.", ex);
        }

        return account;
    }
}
