package thread.cas;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

// blocked waiting 같은 경우 cpu를 사용하지 않지만,
// 스레드 runnable로 while을 통해 계속 확인하는 방법은 cpu를 계속 점유한다.
// 매우 짧게 끝나는 상황에만 cas연산을 사용하자
// spinLock - 바쁜 대기, 스핀 대기 라고 부름 CAS를 활용하여 구현 가능
// CAS 원자적 연산은 많은 동기화 라이브러리에 구현되어있다.
// 하지만 CAS의 정확한 뜻을 알고 접근해야한다.
public class SpinLockMain {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                //sleep(1);
                try {
                    log("비즈니스 로직 실행");
                } finally {
                    spinLock.unlock();
                }
            }
        };

        Thread thread1 = new Thread(runnable, "Thread-1");
        Thread thread2 = new Thread(runnable, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
