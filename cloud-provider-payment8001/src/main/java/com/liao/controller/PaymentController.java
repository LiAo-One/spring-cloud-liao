package com.liao.controller;

import com.liao.entity.Payment;
import com.liao.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    // 服务发现获取服务信息
    private final DiscoveryClient discoveryClient;

    private final PaymentService service;

    @Autowired
    public PaymentController(DiscoveryClient discoveryClient, PaymentService service) {
        this.discoveryClient = discoveryClient;
        this.service = service;
    }

    /**
     * 插入数据
     *
     * @param payment
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public int create(@RequestBody Payment payment) {
        System.out.println("插入成功" + serverPort);
        return service.create(payment);
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    @ResponseBody
    public Payment getPaymentById(@PathVariable("id") Long id) {
        System.out.println("查询成功" + serverPort);
        return service.getPaymentById(id);
    }

    /**
     * 服务发现
     *
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("element=========>" + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            System.out.println("ServiceId======>" + instance.getServiceId());
            System.out.println("Host===========>" + instance.getHost());
            System.out.println("Port===========>" + instance.getPort());
            System.out.println("Uri============>" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/lb")
    @ResponseBody
    public String getPaymentLB() {
        return serverPort;
    }


    @GetMapping("/timout")
    @ResponseBody
    public String paymentFeignTimeout() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

    @GetMapping("/zipkin")
    @ResponseBody
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }

}