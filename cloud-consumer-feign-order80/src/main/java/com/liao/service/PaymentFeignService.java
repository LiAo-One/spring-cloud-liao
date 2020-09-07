package com.liao.service;

import com.liao.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {


    /**
     * ID 查询
     * @param id
     * @return
     */
    @GetMapping("payment/select/{id}")
    public Payment getPaymentById(@PathVariable("id") Long id);


    @GetMapping("payment/timout")
    public String paymentFeignTimeout();
}