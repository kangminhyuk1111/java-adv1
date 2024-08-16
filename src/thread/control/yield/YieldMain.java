package thread.control.yield;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // sleep(1); // sleep
                Thread.yield(); // yield
                // yield - 다른 스레드에게 실행 기회를 주도록 한다. 힌트를 제공할 뿐, 강제성은 없다.
                // Runnable의 상태 두가지 - 실행 대기표에 들어가있는 경우, 실제 실행 중인 경우
            }
        }
    }
}
