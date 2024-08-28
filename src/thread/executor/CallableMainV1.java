package thread.executor;

import java.util.Random;
import java.util.concurrent.*;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class CallableMainV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService es = Executors.newFixedThreadPool(1);
        // future - 미래 -> 미래의 결과를 받을 수 있는 객체
        // future는 전달한 작업의 미래 결과를 담고 있다.
        final Future<Integer> future = es.submit(new MyCallable());

        log("integer = " + future.get());

        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            log("callable start");

            sleep(2000);
            final int value = new Random().nextInt(10);

            log("callable end");
            return value;
        }
    }
}
