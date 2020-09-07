package com.liao.controller;

import com.liao.entity.CommonResult;
import com.liao.entity.Storage;
import com.liao.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/storage")
public class StorageController {

    private final StorageService service;

    @Autowired
    public StorageController(StorageService service) {
        this.service = service;
    }

    /**
     * 扣除库存
     * @param productId 商品id
     * @param count 数量
     * @return
     *
     *  // @PathVariable("id")
     */
    @GetMapping("/decrease")
    @ResponseBody
    public CommonResult<Storage> decrease(@RequestParam(value = "productId",required = true) Long productId,
                                          @RequestParam(value = "count",required = true) Integer count) {
        service.decrease(productId, count);
        return new CommonResult<Storage>(200,"扣减库存成功！");
    }
}