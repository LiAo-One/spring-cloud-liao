package com.liao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.liao.entity.CommonResult;
import com.liao.entity.Payment;
import com.liao.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircleBreakerController {
    public static final String SERVER_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService service;

    @GetMapping("/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback")   // 无配置
    // @SentinelResource(value ="", fallback ="handlerFallback")
    // @SentinelResource(value = "fallback",blockHandler = "blockHandler") // 控制台违规
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",   // 兜底方法
            blockHandler = "blockHandler",  // sentinel 降级
            exceptionsToIgnore = IllegalArgumentException.class) // 忽略的异常 返回Error Page
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("----->参数异常");
        }
        if (result.getData() == null) {
            throw new NullPointerException("参数为空");
        }
        return result;
    }

    /**
     * handlerFallback 兜底异常
     * @param id 参数id
     * @param e 异常session
     * @return 结果
     */
    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(444, e.getMessage(), payment);
    }

    // blockHandler 控制台违规
    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException exception) {
        Payment payment = new Payment(id, null);
        return new CommonResult<Payment>(445, "blockHandler-sentinel限流" + exception.getMessage(), payment);
    }

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return service.paymentSQL(id);
    }
}