package thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class BoundedMain {

    public static void main(String[] args) {
        final BoundedQueue queue = new BoundedQueueV3(2);

        producerFirst(queue);
        //consumerFirst(queue);
    }

    private static void producerFirst(final BoundedQueue queue) {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        final List<Thread> list = new ArrayList<>();
        startProducer(queue, list);
        printAllState(queue, list);
        startConsumer(queue, list);
        printAllState(queue, list);
    }

    private static void consumerFirst(final BoundedQueue queue) {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        final List<Thread> list = new ArrayList<>();
        startConsumer(queue, list);
        printAllState(queue, list);
        startProducer(queue, list);
        printAllState(queue, list);
    }

    private static void startProducer(final BoundedQueue queue, final List<Thread> list) {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            final Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            list.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(final BoundedQueue queue, final List<Thread> list) {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            final Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            list.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(final BoundedQueue queue, final List<Thread> list) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터: " + queue);
        for (Thread thread : list) {
            log(thread.getName() + ": " + thread.getState());
        }
    }
}
