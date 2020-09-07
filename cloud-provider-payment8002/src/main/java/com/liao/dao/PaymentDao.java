package com.liao.dao;

import com.liao.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * TODO:
 *
 * @author LiAo
 * @date 2020/8/4 16:09
 */
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
