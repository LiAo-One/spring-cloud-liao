package com.liao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Applicationcontextconfig {

    @Bean
    // 集群 轮询方式 交替
    // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}