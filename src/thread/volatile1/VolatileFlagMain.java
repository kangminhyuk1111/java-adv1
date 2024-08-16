package thread.volatile1;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class VolatileFlagMain {

    public static void main(String[] args) {
        final MyTask task = new MyTask();
        final Thread t = new Thread(task, "work");

        log("runFlag = " + task.runFlag);

        t.start();
        
        log("runFlag를 false로 변경 시도");

        sleep(1000);
        // false로 변경했음에도 자바가 중료되지 않는다.
        // 멀티스레드는 이미 여러 스레드가 작동해서 안 그래도 이해하기 어려운데, 거기에 한술더하는 문제가있다.
        // 메모리가시성 문제이다.
        // cpu는 처리 성능을 개선하기 위해 중간에 캐시 메모리라는 것을 사용한다.
        // 캐시 메모리 -> 메인 메모리로 최신화는 언제되나요? -> 알 수 없음
        // 주로 컨텍스트 스위칭될 때, 메모리가 갱신된다.

        // 멀티스레드 환경에서 한 스레드가 변경한 값이 다른 스레드에서 언제 보이는 지에 대한 문제를 메모리 가시성 이라한다.

        // volatile -> 캐시 메모리를 사용하지 않고 메인 메모리에 변수에 접근함
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

        //boolean runFlag = true;
        volatile boolean runFlag = true;

        @Override
        public void run() {
           log("task 시작");

           while (runFlag) {
               // runFlag가 false로 변하면 탈출
           }

           log("task 종료");
        }
    }
}
