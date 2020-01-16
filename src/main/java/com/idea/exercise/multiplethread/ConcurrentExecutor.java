package com.idea.exercise.multiplethread;

import com.idea.exercise.multiplethread.concurrent.ConcurrentThread;
import com.idea.exercise.multiplethread.concurrent.EventFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: TangFenQi
 * @description: 性能测试基础类
 * @date：2019/10/15 15:58
 */
public class ConcurrentExecutor {

    /**
     * 趋势测试
     * <p>
     * 小范围不自增
     *
     * @param concurrenceAmount 并发数
     * @param everyAmount       每个线程执行数
     * @param eventHandler      需要测试的方法
     */
    public EventFuture simpleTrendTest(Integer concurrenceAmount, Integer everyAmount,
                                       AbstractEventHandler eventHandler)
            throws InterruptedException {
        EventFuture future = new EventFuture(concurrenceAmount, everyAmount);
        eventHandler.setFuture(future);
        //放入线程
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < concurrenceAmount; i++) {
            new ConcurrentThread(countDownLatch, everyAmount,
                    future, eventHandler)
                    .start();
        }
        Thread.sleep(1000L);
        countDownLatch.countDown();
        return future;
    }
}
