package thread.executor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.MyLogger.log;

public class InvokeAllMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService es = Executors.newFixedThreadPool(10);

        final CallableTask task1 = new CallableTask("task1", 1000);
        final CallableTask task2 = new CallableTask("task2", 2000);
        final CallableTask task3 = new CallableTask("task3", 3000);

        final List<CallableTask> tasks = List.of(task1, task2, task3);

        final List<Future<Integer>> futures = es.invokeAll(tasks);

        for (Future<Integer> future : futures) {
            final Integer integer = future.get();
            log("value = " + integer);
        }

        es.close();
    }
}
