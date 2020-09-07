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

    @Override
    public int create(Payment payment) {
        return dao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return dao.getPaymentById(id);
    }
}