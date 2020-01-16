package com.idea.exercise.multiplethread;

import com.idea.exercise.multiplethread.concurrent.EventFuture;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/10/8 10:29
 */
public abstract class AbstractEventHandler implements IEventHandler {

    private EventFuture future;

    public EventFuture getFuture() {
        return future;
    }

    public void setFuture(EventFuture future) {
        this.future = future;
    }

}
