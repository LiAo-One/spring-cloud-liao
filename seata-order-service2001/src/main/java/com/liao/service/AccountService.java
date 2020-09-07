package com.liao.service;


import com.liao.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;


/**
 *
 * TODO: 余额服务
 * @author LiAo
 * @date 2020/9/7 16:09
 */
@FeignClient("seata-account-service")
public interface AccountService {

    /**
     *  扣余额
     * @param userId 用户id
     * @param money 钱
     * @return
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
