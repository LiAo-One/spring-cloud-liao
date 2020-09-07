package com.liao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/sentinel")
public class FlowLimitController {


    @GetMapping("/testA")
    @ResponseBody
    public String testA()
    {
        /*try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "------testA";
    }

    @GetMapping("/testB")
    @ResponseBody
    public String testB()
    {
        System.out.println(Thread.currentThread().getName());
        return "------testB";
    }

    @GetMapping("/testD")
    @ResponseBody
    public String testD()
    {
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 提高异常比例
        int i = 10 / 0;
        System.out.println("----------->");
        return "------testD";
    }

    @GetMapping("/HotKey")
    @ResponseBody
    // 只保证请求参数 和 并发是否违规
    @SentinelResource(value = "testHotKey",blockHandler = "del_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "testHotKey";
    }

    /**
     * 兜底方法
     * @param p1
     * @param p2
     * @param exception
     * @return
     */
    public String del_testHotKey(String p1, String p2, BlockException exception){
        return "你能不能滚一边去";
    }
}