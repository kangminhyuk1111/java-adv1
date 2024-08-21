package thread.bounded;

import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable{

    private BlockingQueue<String> queue;

    public ConsumerTask(final BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]    ? <- " + queue);
        final String take;
        try {
            take = queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log("[소비 완료] " + take + " <- " + queue);
    }
}
