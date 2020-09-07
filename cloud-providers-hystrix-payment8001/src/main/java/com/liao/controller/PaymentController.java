package com.liao.controller;

import com.liao.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @GetMapping("/hystrix_ok/{id}")
    @ResponseBody
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        return service.paymentInfo_OK(id);

    }

    @GetMapping("/hystrix_timout/{id}")
    @ResponseBody
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        return service.paymentInfo_TimeOut(id);
    }


    //====服务熔断
    @GetMapping("/circuit/{id}")
    @ResponseBody
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = service.paymentCircuitBreaker(id);
        return result;
    }
}