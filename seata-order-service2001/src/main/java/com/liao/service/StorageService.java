package com.liao.service;

import com.liao.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * TODO: 库存服务
 * @author LiAo
 * @date 2020/9/7 16:09
 */
@FeignClient("seata-storage-service")
public interface StorageService {


    /**
     * 扣除库存
     * @param productId 商品id
     * @param count 数量
     * @return
     */
    @GetMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam(value = "productId", required = true) Long productId,
                                 @RequestParam(value = "count", required = true) Integer count);
}
