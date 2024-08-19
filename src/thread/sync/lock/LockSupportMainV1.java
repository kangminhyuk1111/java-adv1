package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        final Thread task = new Thread(new ParkTest(), "Thread-1");

        task.start();

        sleep(100);

        log("Thread-1 state : " + task.getState());

        log("main -> unpark(Thread-1)");
        // LockSupport.unpark(task);
        task.interrupt();
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");

            LockSupport.park();

            log("park 종료: " + Thread.currentThread().getState());
            log("isInterrupted: " + Thread.currentThread().isInterrupted());
        }
    }
}
