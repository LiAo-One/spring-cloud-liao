package com.liao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RefreshScope// 动态刷新
@RequestMapping("/config")
public class ConfigClientController {


    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    @ResponseBody
    public String getConfigInfo() {
        return configInfo;
    }
}