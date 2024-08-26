package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // cas 연산 => 값이 0 이 value 1로 변경
        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println(result1 + " value = " + atomicInteger.get());

        // 값을 확인하고 true 이면 값을 변경함. 이것을 하나의 원자적 연산으로 모음
        boolean result2 = atomicInteger.compareAndSet(0, 1);
        System.out.println(result2 + " value = " + atomicInteger.get());


    }
}
