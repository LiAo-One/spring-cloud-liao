package com.liao.service.impl;

import com.liao.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------PaymentHystrixService fall back-----滚！！";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-------PaymentHystrixService fall back-----滚！！";
    }
}