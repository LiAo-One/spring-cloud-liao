package com.liao.controller;

import com.liao.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mess")
public class SendMessageController {

    @Autowired
    private MessageProvider provider;

    @GetMapping("/send")
    @ResponseBody
    public String sendMessage(){

        return provider.send();
    }
}