package thread.cas;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

// 여러 스레드가 자주 값을 변경하면 성능저하가 발생함
// 반복문을 돌면 cpu 자원을 소모하고 있는 것임
// 락 - 비관적 - 반드시 입구가 하나만 존재함, 락을 무조건 걸어버림 - 성능이 좋지 못함
// cas - 낙관적 - 락을 사용하지 않고 변경하지만, 충돌이 발생하면 그때 재시도 함
// 간단한 cpu 연산은 초당 수억개의 연산을 하는 cpu에게 적은 부하이므로 cas를 통해 처리하자
public class CasMainV3 {

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("atomicInteger = " + atomicInteger);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + "result value = " + result);
    }

    private static int incrementAndGet(AtomicInteger integer) {
        int getValue;
        boolean result;
        do {
            getValue = integer.get();
            sleep(10);
            log("getValue = " + getValue);
            result = integer.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while (!result);

        return getValue + 1;
    }
}
