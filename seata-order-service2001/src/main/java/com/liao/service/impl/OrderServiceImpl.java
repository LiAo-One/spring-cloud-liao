package com.liao.service.impl;

import com.liao.dao.OrderDao;
import com.liao.entity.Order;
import com.liao.service.AccountService;
import com.liao.service.OrderService;
import com.liao.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    // 订单dao
    private final OrderDao dao;

    // 库存
    private final StorageService storageService;

    // 用户余额
    private final AccountService accountService;

    @Autowired
    public OrderServiceImpl(OrderDao dao, StorageService storageService, AccountService accountService) {
        this.dao = dao;
        this.storageService = storageService;
        this.accountService = accountService;
    }

    @Override
    public void create(Order order) {
        System.out.println("----->开始新建订单");
        //1 新建订单
        dao.create(order);

        //2 扣减库存
        System.out.println("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("----->订单微服务开始调用库存，做扣减end");

        //3 扣减账户
        System.out.println("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney().multiply(new BigDecimal(order.getCount())));
        System.out.println("----->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从零到1,1代表已经完成
        System.out.println("----->修改订单状态开始");
        dao.update(order.getUserId(),0);
        System.out.println("----->修改订单状态结束");

        System.out.println("----->下订单结束了，O(∩_∩)O哈哈~");
    }
}