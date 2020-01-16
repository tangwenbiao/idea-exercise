package com.idea.exercise.concurrent;


import com.idea.exercise.multiplethread.AbstractEventHandler;
import com.idea.exercise.multiplethread.CapabilityBaseExecutor;
import com.idea.exercise.multiplethread.concurrent.EventFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(JUnit4.class)
@SpringBootTest(classes = ConcurrentTestApplication.class)
public class ConcurrentTest {

    @Test
    public void testConcurrent() {
        CapabilityBaseExecutor executor = new CapabilityBaseExecutor();
        EventFuture future;
        try {
            future = executor.simpleTrendTest(10, 200, new TestHandler());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程被打断。");
            return;
        }
        Map<Object, Object> objectObjectMap = future.get();
        Integer count = (Integer) objectObjectMap.get("count");
        System.out.println("方法结果:" + count);
        future.print();
    }

    private static class TestHandler extends AbstractEventHandler {

        AtomicInteger count = new AtomicInteger(0);

        @Override
        public void handle() {
            count.addAndGet(1);
            EventFuture future = getFuture();
            future.put("count", count.get());
        }
    }

}
