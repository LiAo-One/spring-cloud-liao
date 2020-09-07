package com.liao.controller;

import com.liao.entity.Payment;
import com.liao.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @ResponseBody
    public int create(@RequestBody Payment payment) {
        System.out.println("插入成功" + serverPort);
        return service.create(payment);
    }

    @GetMapping("/select/{id}")
    @ResponseBody
    public Payment getPaymentById(@PathVariable("id") Long id) {
        System.out.println("查询成功" + serverPort);
        return service.getPaymentById(id);
    }

    @GetMapping("/lb")
    @ResponseBody
    public String getPaymentLB(){
        return serverPort;
    }
}