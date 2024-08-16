package thread.control.interrupt;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV4 {

    public static void main(String[] args) {
        final MyTask task = new MyTask();

        final Thread thread = new Thread(task, "work");

        thread.start();

        // Interrupt
        // 프로세스 실행 도중 예기치 않은 상황이 발생할 때 발생한 상황을 처리한 후, 실행중인 작업으로 복귀하는 것을 말한다.
        sleep(10);
        thread.interrupt();
        // thread.stop(); -> 강제 종료시 자원 정리할 시간이 주어지지 않기 때문에 잘못됨
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                // Thread.interrupted - 인터럽트 상태 변경 O
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리");
                Thread.sleep(1000);
                log("자원 종료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }
            log("자원 종료");
        }
    }
}
