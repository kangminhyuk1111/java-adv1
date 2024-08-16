package thread.sync;

import java.util.ArrayList;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class BankMain {

    static int result = 0;

    public static void main(String[] args) throws InterruptedException {
        final BankAccount account = new BankAccountV1(1000);

        final Thread t1 = new Thread(new WithdrawTask(account, 800), "t1");
        final Thread t2 = new Thread(new WithdrawTask(account, 800), "t2");

        t1.start();
        t2.start();

        sleep(500);
        log("t1 state : " + t1.getState());
        log("t2 state : " + t2.getState());

        t1.join();
        t2.join();

        log("최종 잔액: " + account.getBalance());
    }
}
