package thread.control.join;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class JoinMainV2 {

    public static void main(String[] args) {
        log("start");

        final SumTask task1 = new SumTask(1, 50);
        final SumTask task2 = new SumTask(51, 100);

        final Thread thread1 = new Thread(task1, "thread-1");
        final Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // 정확한 타이밍을 맞추어 기다리기 어려움
        log("main 스레드 sleep()");

        // sleep 메서드를 사용해서 task1, task2의 필드변수에 작업이 완료되어 값이 할당되기를 기다림.
        sleep(3000);

        // 깨어났을때는 값이 할당 되어 있음.
        log("main 스레드 깨어남");

        log(task1.result);
        log(task2.result);

        int sumAll = task1.result + task2.result;

        log("sumAll = " + sumAll);

        log("end");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(final int startValue, final int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }
        
        @Override
        public void run() {
            log("작업 시작");

            sleep(2000);

            int sum = 0;
            for (int i = startValue; i < endValue; i++) {
                sum += i;
            }

            result = sum;

            log("작업 완료");
        }
    }
}
