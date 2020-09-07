package com.liao.lb.Impl;

import com.liao.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * TODO: 自定义轮询算法 实现
 *
 * @author LiAo
 * @date 2020/8/13 20:55
 */
@Component
public class LoadBalancerImpl implements LoadBalancer {

    // 初始值
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("==========>"+next);
        return next;
    }

    /**
     * 轮询算法
     * @param serviceInstanceList
     * @return
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {

        int index = getAndIncrement() % serviceInstanceList.size();

        return serviceInstanceList.get(index);
    }
}