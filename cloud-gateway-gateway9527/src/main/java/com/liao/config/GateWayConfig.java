package com.liao.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator locator(RouteLocatorBuilder builder){

        // 创建路由
        RouteLocatorBuilder.Builder routes = builder.routes();

        /**
         * path_route_01 路由名称
         * lady 访问的地址
         * http://news.baidu.com/lady 映射的地址
         */
        routes.route("path_route_01",
                r -> r.path("/lady").uri("http://news.baidu.com/lady")).build();
        routes.route("path_route_02",
                r -> r.path("/internet").uri("http://news.baidu.com/internet")).build();
        return routes.build();
    }
}