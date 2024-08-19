package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class BankAccountV4 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(final int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(final int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        lock.lock();

        try {
            log("[검증 시작]");
            if (balance < amount) {
                log("[검증 실패]");
                return false;
            }

            log("[검증 완료]");
            // 검증 완료후 출금
            sleep(1000);
            balance = balance - amount;
        } finally {
            lock.unlock();
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}