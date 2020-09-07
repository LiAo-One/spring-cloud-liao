package com.liao.service.impl;

import com.liao.dao.StorageDao;
import com.liao.service.StorageService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageDao dao;

    @Autowired
    public StorageServiceImpl(StorageDao dao) {
        this.dao = dao;
    }

    /**
     * 扣除库存
     * @param productId 产品id
     * @param count 数量
     */
    @Override
    public void decrease(Long productId, Integer count) {
        System.out.println("------->storage-service中扣减库存开始");
        dao.decrease(productId,count);
        System.out.println("------->storage-service中扣减库存结束");
    }
}