package com.idea.exercise.multiplethread.concurrent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EventFuture implements Future {

    private Map<Object, Object> verifiedData;

    private LocalDateTime createTime;

    private LocalDateTime endTime;

    //总位置
    private int total;

    //错误计数
    private AtomicInteger errCount;

    //错误集合
    private List<Throwable> errInfos;

    //当前执行的位置
    private AtomicInteger count;

    private boolean cancel;

    public EventFuture(int concurrenceAmount, int everyAmount) {
        this.total = concurrenceAmount * everyAmount;
        errCount = new AtomicInteger(0);
        count = new AtomicInteger(0);
        errInfos = new CopyOnWriteArrayList<>();
        verifiedData = new ConcurrentHashMap<>();
        cancel = false;
        createTime = LocalDateTime.now();
    }

    public boolean isEnd() {
        return count.get() == total;
    }

    public void put(Object key, Object value) {
        verifiedData.put(key, value);
    }

    public Object get(Object key) {
        return verifiedData.get(key);
    }

    protected void count() {
        int currentCount = count.addAndGet(1);
        if (currentCount == total) {
            endTime = LocalDateTime.now();
        }
    }

    protected void addErr(Throwable throwable) {
        errCount.addAndGet(1);
        errInfos.add(throwable);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return cancel = mayInterruptIfRunning;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public boolean isDone() {
        return !isEnd();
    }

    @Override
    public Map<Object, Object> get(){
        while (true) {
            if (isEnd()) {
                return verifiedData;
            }
        }
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new IllegalStateException("this method is not support!");
    }

    public void print() {
        if (endTime != null) {
            Duration duration = Duration.between(createTime, endTime);
            System.out.println("整体时间(单位毫秒):" + duration.getNano()/1000);
        }
        System.out.println("异常的数量:" + errCount.get() + " 总体数量:" + total);
    }
}
