package com.liao.lb;


import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 *
 * TODO: 自定义轮询
 * @author LiAo
 * @date 2020/8/13 20:52
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstanceList);
}