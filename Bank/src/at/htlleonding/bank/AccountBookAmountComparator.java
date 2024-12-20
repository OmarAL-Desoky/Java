package at.htlleonding.bank;

import java.util.Comparator;

public class AccountBookAmountComparator implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        return Double.compare(o1.getBalance(), o2.getBalance());
    }
}
