package com.idea.exercise.multiplethread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: TangFenQi
 * @description: 并发流程处理基类
 * @date：2019/10/8 10:19
 */
public abstract class AbstractConcurrentThread extends Thread {

    /**
     * 并发信号枪
     */
    protected CountDownLatch countDownLatch;

    /**
     * 返回结果
     */
    protected EventFuture eventFuture;

    /**
     * 执行次数
     */
    protected Integer amount;

    public AbstractConcurrentThread(CountDownLatch countDownLatch, Integer amount, EventFuture future) {
        this.countDownLatch = countDownLatch;
        this.amount = amount;
        this.eventFuture = future;
    }
}
