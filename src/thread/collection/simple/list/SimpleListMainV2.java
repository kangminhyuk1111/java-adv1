package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    // 프록시? - 대리자, 대신 처리해주는 사람

    public static void main(String[] args) throws InterruptedException {
        test(new SyncProxyList(new BasicList()));
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable taskA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1 add A");
            }
        };

        Runnable taskB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2 add B");
            }
        };

        Thread thread1 = new Thread(taskA, "Thread-1");
        Thread thread2 = new Thread(taskB, "Thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        log(list);
    }
}
