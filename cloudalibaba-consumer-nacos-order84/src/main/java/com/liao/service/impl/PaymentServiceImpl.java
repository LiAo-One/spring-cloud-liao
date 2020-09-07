package com.liao.service.impl;

import com.liao.entity.CommonResult;
import com.liao.entity.Payment;
import com.liao.service.PaymentService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PaymentServiceImpl implements PaymentService {


    /**
     * 服务降级
     * @param id
     * @return
     */
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444,"服务降级返回----->PaymentService",new Payment(id,"error"));
    }
}