package com.idea.exercise.multiplethread.concurrent;
import com.idea.exercise.multiplethread.CapabilityBaseExecutor;
import com.idea.exercise.multiplethread.AbstractEventHandler;

/**
 * @author: TangFenQi
 * @description:
 * @date：2019/4/25 17:08
 */
public class ConcurrentCapabilityService {

  private CapabilityBaseExecutor executor = new CapabilityBaseExecutor();

  /**
   * 趋势测试
   * <p>
   * 小范围不自增
   *
   * @param amount 测试条数
   */
  public void simpleTrendTest(Integer amount, AbstractEventHandler eventHandler)
      throws InterruptedException {
    //设置线程数
    Integer concurrenceAmount = Runtime.getRuntime().availableProcessors() * 2;
    //每个线程需要执行的次数
    Integer everyAmount = amount / concurrenceAmount;
    executor.simpleTrendTest(concurrenceAmount, everyAmount, eventHandler);
  }

}
