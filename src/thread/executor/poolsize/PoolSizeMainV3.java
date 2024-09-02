package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.utils.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV3 {

    static final int TASK_SIZE = 1100;
//    static final int TASK_SIZE = 1200;
//    static final int TASK_SIZE = 1300;

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        printState(es);
        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }
        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));
    }
}
