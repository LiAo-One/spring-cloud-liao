package com.liao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/payment")
public class OrderZKController {
    public static final String INVOKE_URL = "http://cloud-provider-payment";

    private final RestTemplate restTemplate;

    @Autowired
    public OrderZKController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/zk")
    @ResponseBody
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}