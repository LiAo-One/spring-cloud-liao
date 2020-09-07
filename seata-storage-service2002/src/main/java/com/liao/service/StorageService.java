package com.liao.service;

public interface StorageService {

    /**
     * 扣除库存
     * @param productId 产品id
     * @param count 数量
     */
    void decrease(Long productId, Integer count);
}