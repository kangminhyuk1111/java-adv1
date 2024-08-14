package thread.control.interrupt;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV3 {

    public static void main(String[] args) {
        final MyTask task = new MyTask();

        final Thread thread = new Thread(task, "work");

        thread.start();

        // Interrupt
        // 프로세스 실행 도중 예기치 않은 상황이 발생할 때 발생한 상황을 처리한 후, 실행중인 작업으로 복귀하는 것을 말한다.
        sleep(100);
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            log("자원 정리");
            log("자원 종료");
        }
    }
}
