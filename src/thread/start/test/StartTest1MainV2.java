package thread.start.test;

import static util.MyLogger.log;

public class StartTest1MainV2 {

    public static void main(String[] args) {

        final Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    log("value: " + (i+1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }
}
