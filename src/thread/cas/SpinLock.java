package thread.cas;

import java.util.concurrent.atomic.AtomicBoolean;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

// 원자적이지 않은 연산을 원자적인 연산으로 변경 SpinLockBad -> SpinLock
public class SpinLock {

    private final AtomicBoolean locked = new AtomicBoolean(false);

    public void lock() {
        log("lock 획득 시도");
        while (!locked.compareAndSet(false,true)){
            log("락 획득 실패 스핀 대기");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        locked.set(false);
        log("lock 반납 완료");
    }
}
