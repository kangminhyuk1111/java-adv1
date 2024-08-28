package thread.executor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class InvokeAnyMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService es = Executors.newFixedThreadPool(10);

        final CallableTask task1 = new CallableTask("task1", 1000);
        final CallableTask task2 = new CallableTask("task2", 2000);
        final CallableTask task3 = new CallableTask("task3", 3000);

        final List<CallableTask> tasks = List.of(task1, task2, task3);

        final Integer value = es.invokeAny(tasks);

        log("value = " + value);

        es.close();
    }
}
