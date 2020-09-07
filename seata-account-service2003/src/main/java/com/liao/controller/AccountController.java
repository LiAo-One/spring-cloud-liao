package com.liao.controller;

import com.liao.entity.Account;
import com.liao.entity.CommonResult;
import com.liao.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping("/decrease")
    @ResponseBody
    public CommonResult<Account> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        service.decrease(userId,money);
        return new CommonResult<Account> (200,"扣减账户余额成功！");
    }
}