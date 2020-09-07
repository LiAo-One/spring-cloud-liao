package com.liao.controller;

import com.liao.entity.Payment;
import com.liao.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    // public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    // 自定义轮询算法
    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 添加
     *
     * @param payment 添加参数
     * @return 添加结果
     */
    @GetMapping("/create")
    @ResponseBody
    public Integer create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Integer.class);
    }

    /**
     * ID 查询
     *
     * @param id 查询参数
     * @return 查询结果
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public Payment getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/select/" + id, Payment.class);
    }

    /**
     * getForEntity
     * ID 查询
     *
     * @param id 查询参数
     * @return 查询结果
     */
    @GetMapping("/get_entity/{id}")
    @ResponseBody
    public Payment getForEntity(@PathVariable("id") Long id) {

        ResponseEntity<Payment> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/select/" + id, Payment.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            System.out.println("=======================>" + entity);
            return entity.getBody();
        } else {
            return new Payment(1L, "失败");
        }
    }

    /**
     * 添加
     *
     * @param payment 添加参数
     * @return 添加结果
     */
    @GetMapping("/post_entity")
    @ResponseBody
    public Integer postForEntity(Payment payment) {

        ResponseEntity<Integer> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, Integer.class);

        if (entity.getStatusCode().is2xxSuccessful()){
            System.out.println("=============>" + entity);
            return entity.getBody();
        }else {
            return 0;
        }
    }


    @GetMapping("/lb")
    @ResponseBody
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }


    // ====================> zipkin+sleuth
    @GetMapping("/zipkin")
    @ResponseBody
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}