package thread.sync;

public class WithdrawTask implements Runnable{

    private BankAccount bankAccount;
    private int amount;

    public WithdrawTask(final BankAccount bankAccount, final int amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        bankAccount.withdraw(amount);
    }
}
