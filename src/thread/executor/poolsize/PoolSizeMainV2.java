package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.utils.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService es;
        es = Executors.newSingleThreadExecutor();
//        es = Executors.newFixedThreadPool(10);
//        es = Executors.newCachedThreadPool();

        log("pool 생성");

        printState(es);

        for (int i = 1; i <= 200; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("==shutdown==");
    }
}
