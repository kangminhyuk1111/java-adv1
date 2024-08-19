package thread.sync;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class BankAccountV2 implements BankAccount {

    private int balance;

    public BankAccountV2(final int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(final int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        // 잔액보다 적으면 false
        log("[검증 시작]");
        if(balance < amount) {
            log("[검증 실패]");
            return false;
        }

        log("[검증 완료]");
        // 검증 완료후 출금
        sleep(1000);
        balance = balance - amount;

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
