package thread.executor;

import java.util.concurrent.*;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class SumTaskMainV1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // future는 미래 작업의 계산의 결과를 나타냄
        // cancel - 아직 완료되지 않은 작업을 취소한다.
        // isCancelled - 작업이 취소 되었는지 여부 확인
        // isDone - 작업이 끝났는지 여부
        // state - future의 상태 반환 / running success failed cancelled

        final ExecutorService es = Executors.newFixedThreadPool(4);

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        SumTask task3 = new SumTask(51, 100);
        SumTask task4 = new SumTask(51, 100);

        final long start = System.currentTimeMillis();

        final Future<Integer> submit1 = es.submit(task1);
        final Future<Integer> submit2 = es.submit(task2);

        log("main 스레드 대기 완료");

        log("task1.result=" + submit1.get());
        log("task2.result=" + submit2.get());

        final long end = System.currentTimeMillis();

        log("================startend===========" + start + "========="+ end);

        final Future<Integer> submit3 = es.submit(task3);
        final Future<Integer> submit4 = es.submit(task4);

        log("task3.result=" + submit3.get());
        log("task4.result=" + submit4.get());

        int sumAll = submit1.get() + submit2.get();
        int sumAll2 = submit1.get() + submit2.get() + submit3.get() + submit4.get();

        log("task1 + task2 = " + sumAll);
        log("task1 + task2 = " + sumAll2);
        log("End");
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
