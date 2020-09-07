package com.liao.service.impl;

import com.liao.dao.AccountDao;
import com.liao.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao dao;

    @Autowired
    public AccountServiceImpl(AccountDao dao) {
        this.dao = dao;
    }

    @Override
    public void decrease(Long userId, BigDecimal money) {
        System.out.println("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚 OpenFeign 默认时间一秒钟 否则弹出超时异常
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        dao.decrease(userId,money);
        System.out.println("------->account-service中扣减账户余额结束");
    }
}