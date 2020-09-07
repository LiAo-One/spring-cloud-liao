package com.liao.dao;

import org.apache.ibatis.annotations.Param;

public interface StorageDao {

    /**
     * 扣减商品库存
     * @param productId 商品id
     * @param count 数量
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
