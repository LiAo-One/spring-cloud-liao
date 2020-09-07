package com.liao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/nacos")
public class EchoController {


    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/echo/{str}")
    @ResponseBody
    public String echo(@PathVariable String str) {
        return "Hello Nacos Discovery " + str+"------>"+serverPort;
    }
}