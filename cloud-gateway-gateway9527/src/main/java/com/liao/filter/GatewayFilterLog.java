package com.liao.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 自定义全局过滤器
 * d
 */
@Component
public class GatewayFilterLog implements GlobalFilter, Ordered {


    /**
     * 打印日志 并判断请求
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("come in GatewayFilterLog--------->" + new Date());
        // 得到请求参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        // uname 如果为空
        if (uname == null){
            System.out.println("-----------滚出去");
            // 设置http请求状态码 为不被接收
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //  返回错误信息
            return exchange.getResponse().setComplete();
        }
        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}