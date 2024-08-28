package thread.executor;

import java.util.concurrent.*;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class SumTaskMainTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(task1);
        Future<Integer> future2 = es.submit(task2);

        log("작업 시작");
        long st = System.currentTimeMillis();
        Integer sum1 = future1.get();
        long end = System.currentTimeMillis();
        Integer sum2 = future2.get();
        log(end - st + "ms");
        log("task1.result = " + sum1);
        log("task2.result = " + sum2);

        int sumAll = sum1 + sum2;
        log("task1 + task2 = " + sumAll);

        es.close();
    }

    static class SumTask implements Callable {
        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() {
            log("작업 시작");

            sleep(2000);

            int sum = 0;

            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            log("작업 완료 sum = " + sum);

            return sum;
        }
    }
}
