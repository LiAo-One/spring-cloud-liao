package com.liao.service;

import com.liao.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    /**
     * 添加
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * ID 查询
     * @param id
     * @return
     */
    public Payment getPaymentById(@Param("id") Long id);
}