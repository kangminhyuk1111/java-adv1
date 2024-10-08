package thread.control.join;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class JoinMainV1 {

    public static void main(String[] args) {
        log("start");

        // this는 호출된 인스턴스 메서드가 소속된 객체를 가리키는 참조이며, 이것이 스택 프레임 내부에 저장되어 있다.

        final SumTask task1 = new SumTask(1, 50);

        // task2는 this로 task2의 참조값 주소를 가지고 있다. this.task2 하면 참조값 찾아감.
        final SumTask task2 = new SumTask(51, 100);

        final Thread thread1 = new Thread(task1, "thread-1");
        final Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

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
