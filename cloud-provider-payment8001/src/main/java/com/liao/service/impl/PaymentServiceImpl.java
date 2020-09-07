package com.liao.service.impl;

import com.liao.dao.PaymentDao;
import com.liao.entity.Payment;
import com.liao.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao dao;

    @Autowired
    public PaymentServiceImpl(PaymentDao dao) {
        this.dao = dao;
    }

    /**
     * 添加
     * @param payment
     * @return
     */
    @Override
    public int create(Payment payment) {
        return dao.create(payment);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public Payment getPaymentById(Long id) {
        return dao.getPaymentById(id);
    }
}