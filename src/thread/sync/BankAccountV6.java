package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class BankAccountV6 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(final int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(final int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("이미 처리중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
