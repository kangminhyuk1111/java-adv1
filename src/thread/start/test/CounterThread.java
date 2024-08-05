package thread.start.test;

import static util.MyLogger.log;

public class CounterThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log("value: " + (i+1));
        }
    }
}
