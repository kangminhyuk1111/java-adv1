package thread.executor;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;

public class CallableMainV2 {

    private static final int COUNT = 1000000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService es = Executors.newFixedThreadPool(3);
        log("submit 호출");

        final Future<Integer> future = es.submit(new MyCallable());
        final Future<Integer> future2 = es.submit(new MyCallable());

        log("future 즉시 반환, future = " + future);
        log("future 즉시 반환, future = " + future2);

        log("main waiting");

        log("future = " + future.get());
        log("future2 = " + future2.get());

        log("main runnable");

        log("future 완료");

        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("callable start");

            final int value = new Random().nextInt(10);

            log("callable end");
            return value;
        }
    }
}
