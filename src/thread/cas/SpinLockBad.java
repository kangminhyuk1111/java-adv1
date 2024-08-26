package thread.cas;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class SpinLockBad {

    private volatile boolean locked = false;

    public void lock() {
        log("lock 획득 시도");
        while (true) {
            if(!locked) {
                sleep(100);
                locked = true;
                break;
            } else {
                log("lock 획득 실패 spin 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        locked = false;
        log("lock 반납 완료");
    }
}
