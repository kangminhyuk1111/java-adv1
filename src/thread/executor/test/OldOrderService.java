package thread.executor.test;

import java.util.concurrent.*;

import static thread.control.ThreadUtils.sleep;
import static util.MyLogger.log;

public class OldOrderService {

    public void order(String orderNo) {
        final ExecutorService es = Executors.newFixedThreadPool(3);

        final Future<Boolean> inventorySubmit = es.submit(new InventoryWork(orderNo));
        final Future<Boolean> shippingSubmit = es.submit(new ShippingWork(orderNo));
        final Future<Boolean> accountSubmit = es.submit(new AccountingWork(orderNo));

        // 결과 확인
        try {
            final Boolean inventoryResult = inventorySubmit.get();
            final Boolean shippingResult = shippingSubmit.get();
            final Boolean accountResult = accountSubmit.get();
            if (inventoryResult && shippingResult && accountResult) {
                log("모든 주문 처리가 성공적으로 완료되었습니다.");
            }
            else {
                log("일부 작업이 실패했습니다.");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            es.close();
        }
    }

    static class InventoryWork implements Callable<Boolean> {

        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork implements Callable<Boolean> {

        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("배송 시스템 알림: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {

        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("회계 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
