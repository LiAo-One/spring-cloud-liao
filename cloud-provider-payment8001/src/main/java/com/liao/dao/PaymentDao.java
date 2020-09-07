package com.liao.dao;

import com.liao.entity.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO:
 *
 * @author LiAo
 * @date 2020/8/4 16:09
 */
public interface PaymentDao {

    /**
     * 添加
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * id 查询
     * @param id
     * @return
     */
    public Payment getPaymentById(@Param("id") Long id);
}
