package com.liao.controller;

import com.liao.entity.CommonResult;
import com.liao.entity.Order;
import com.liao.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.Transient;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/create")
    @ResponseBody
    @GlobalTransactional(name = "wick_group",rollbackFor = Exception.class)
    public CommonResult create(Order order)
    {
        service.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}