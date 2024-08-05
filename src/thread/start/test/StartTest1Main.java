package thread.start.test;

public class StartTest1Main {

    public static void main(String[] args) {
        final Runnable runnable = new CounterThread();
        final Thread thread = new Thread(runnable);
        thread.start();
    }
}
