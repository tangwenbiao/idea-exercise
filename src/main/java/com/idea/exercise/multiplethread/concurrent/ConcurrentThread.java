package com.idea.exercise.multiplethread.concurrent;

import com.idea.exercise.multiplethread.AbstractEventHandler;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/4/25 17:20
 */
public class ConcurrentThread extends AbstractConcurrentThread {

    private AbstractEventHandler eventHandler;
    private EventFuture future;

    public ConcurrentThread(CountDownLatch countDownLatch, Integer amount,
                            EventFuture future,
                            AbstractEventHandler eventHandler) {
        super(countDownLatch, amount, future);
        this.eventHandler = eventHandler;
        this.future = future;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("start! thread id:" + Thread.currentThread().getId());
        for (int i = 0; i < amount; i++) {
            if (future.isCancelled()) {
                return;
            }
            try {
                eventHandler.handle();
            } catch (Exception ex) {
                future.addErr(ex);
            } finally {
                future.count();
            }
        }
        System.out.println("end! thread id:" + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("30").divide(new BigDecimal(100)));
    }
}
