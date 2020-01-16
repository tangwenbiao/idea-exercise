package com.idea.exercise.multiplethread.single;

import com.idea.exercise.multiplethread.CapabilityBaseExecutor;
import com.idea.exercise.multiplethread.AbstractEventHandler;

/**
 * @author: TangFenQi
 * @description: 单线程性能测试
 * @date：2019/10/15 16:03
 */
public class SingleCapabilityService {

  private CapabilityBaseExecutor baseService = new CapabilityBaseExecutor();

  public void capabilityTest(AbstractEventHandler businessService) throws InterruptedException {
    baseService.simpleTrendTest(1,1,businessService);
  }

}
