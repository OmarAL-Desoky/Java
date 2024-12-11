package at.htlleonding.bank;

public class YouthAccount extends Account {
    private static final int MIN_AGE = 14;
    private static final int MAX_AGE = 18;
    private static final double BONUS_BALANCE = 20;

    private int age;

    public void setAge(int value) {
        if(value < MIN_AGE || value > MAX_AGE) {
            throw new IllegalArgumentException();
        }

        age = value;
    }

    public int getAge() {
        return age;
    }

    @Override
    protected double getDefaultMaxOverdraft() {
        return 0;
    }

    public YouthAccount(int accountNumber, int age) {
        super(accountNumber);
        setAge(age);
        balance = BONUS_BALANCE;
    }

    @Override
    public void setMaxOverdraft(double maxOverdraft) {
        if(maxOverdraft != 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ";" + age;
    }
}