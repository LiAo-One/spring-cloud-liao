package com.liao.controller;

import com.liao.entity.Payment;
import com.liao.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("abc")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService service;

    @GetMapping("/select/{id}")
    @ResponseBody
    public Payment getPayment(@PathVariable("id") Long id){
        return service.getPaymentById(id);
    }


    @GetMapping("/timout")
    @ResponseBody
    public String paymentFeignTimeout(){
        // 客户端默认等待一秒钟 超过一秒报错
        return service.paymentFeignTimeout();
    }
}