package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class CasMainV2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // incrementAndGet
        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println(resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println(resultValue2);
    }

    private static int incrementAndGet(AtomicInteger integer) {
        int getValue;
        boolean result;
        do {
            getValue = integer.get();
            log("getValue = " + getValue);
            result = integer.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        } while (!result);

        return getValue + 1;
    }

    // 멀티스레드 사용해서 다른 스레드가 만약 값을 변경해버리는 경우가 있다면
}
