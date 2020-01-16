package com.idea.exercise.authselection;


import com.idea.exercise.authselection.test.TestConcurrent;
import com.idea.exercise.multiplethread.AbstractEventHandler;
import com.idea.exercise.multiplethread.ConcurrentExecutor;
import com.idea.exercise.multiplethread.concurrent.EventFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Random;

@SpringBootTest(classes = {AuthTestApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ConcurrentOperationTest {

    @Test
    public void test() {
        ConcurrentExecutor executor = new ConcurrentExecutor();
        AuthConcurrentHandler handler = new AuthConcurrentHandler(new AuthService());
        TestConcurrent.threadLocal.set(new TestConcurrent.OperatorLink());
        EventFuture future;
        try {
            future = executor.simpleTrendTest(1, 3, handler);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        Map<Object, Object> objectObjectMap = future.get();
        future.print();

        TestConcurrent.OperatorLink operatorLink = TestConcurrent.threadLocal.get();
        System.out.println(operatorLink.calculate());

    }

    private static class AuthConcurrentHandler extends AbstractEventHandler {

        private AuthService authService;
        private long userId;

        public AuthConcurrentHandler(AuthService authService) {
            this.authService = authService;
            userId = 1L;
        }

        @Override
        public void handle() {
            Random random = new Random();
            int value = random.nextInt(4);
            AuthEnum authEnum = randomAuth();
            if (value < 2) {
                authService.add(userId, authEnum);
                System.out.println("新增:" + authEnum.name());
            } else {
                authService.deleted(userId, randomAuth());
                System.out.println("删除:" + authEnum.name());
            }
        }

        private AuthEnum randomAuth() {
            Random random = new Random();
            int i = random.nextInt(4);
            switch (i) {
                case 0:
                    return AuthEnum.ADD;
                case 1:
                    return AuthEnum.UPDATE;
                case 2:
                    return AuthEnum.DELETED;
                case 3:
                    return AuthEnum.SELECT;
                default:
                    throw new IllegalArgumentException("this number is not support ");
            }
        }
    }


}
