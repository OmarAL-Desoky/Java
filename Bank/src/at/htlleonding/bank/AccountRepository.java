package at.htlleonding.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static AccountRepository instance;

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    private List<Account> accounts;

    private AccountRepository() {
        this.accounts = new ArrayList<Account>();
    }

    public boolean addAccount(Account account) {
        boolean addedAcc = false;

        if(!accounts.contains(account)) {
            this.accounts.add(account);
            addedAcc = true;
        }

        return addedAcc;
    }

    public Account getAccount(int accountNumber) {
        Account result = null;
        boolean contains = false;

        for(int i = 0; i < this.accounts.size() && !contains; i++) {
            if(this.accounts.get(i).getAccountNumber() == accountNumber) {
                result = this.accounts.get(i);
                contains = true;
            }
        }

        return result;
    }

    public void loadAccounts(String accountsPath) {
        try {
            Path path = Paths.get(accountsPath);
            List<String> lines = Files.readAllLines(path);

            for(String line : lines) {
                Account account = Account.createFromString(line);

                if(!this.accounts.contains(account)) {
                    this.accounts.add(account);
                }
            }
        }
        catch(IOException e) {
            throw new AccountPersistenceException("Could not read from accounts file.", e);
        }
    }

    public void writeAccounts(String accountsPath) {
        try {
            Path path = Paths.get(accountsPath);

            for(Account account : this.accounts) {
                Files.writeString(path, account.toString() + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        }
        catch(IOException e) {
            throw new AccountPersistenceException("Could not write to accounts file.", e);
        }
    }
}
