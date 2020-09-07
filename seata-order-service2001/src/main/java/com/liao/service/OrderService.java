package com.liao.service;

import com.liao.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
