package com.liao.controller;

import com.liao.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumer/payment")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController {

    @Autowired
    private PaymentHystrixService service;


    /**
     * 正常方法
     * @param id
     * @return
     */
    @GetMapping("/hystrix_ok/{id}")
    @ResponseBody
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return service.paymentInfo_OK(id);
    }


    /**
     * 报错方法
     * @param id
     * @return
     */
    @GetMapping("/hystrix_timout/{id}")
    @ResponseBody
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    })*/
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return service.paymentInfo_TimeOut(id);
    }

    /**
     * 出错时运行的方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandle(Integer id){
        return "服务器出错了     ======>   你能不能滚一边去啊";
    }


    /**
     * 全局兜底方法
     * @return
     */
    public String payment_Global_FallbackMethod()
    {
        return "都给我滚";
    }
}