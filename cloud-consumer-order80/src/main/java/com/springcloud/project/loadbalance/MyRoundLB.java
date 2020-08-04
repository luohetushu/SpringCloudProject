package com.springcloud.project.loadbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyRoundLB implements LoadBalancer {

    Logger logger = LoggerFactory.getLogger(MyRoundLB.class);

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }


    public final int getAndIncrement(){
        int current;
        int next;
        //自旋锁
        do{
            current = this.atomicInteger.get();
            next = current > 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        logger.debug("-----第几次访问，访问次数：{}", next);
        return next;
    }


}
