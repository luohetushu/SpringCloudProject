package com.springcloud.project.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关路由配置类
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();

        routes.route("path_route_guonei",
                r -> r.path("/payment/guonei")         // HTTP ERROR 500  ?????
                        .uri("http://news.baidu.com/guonei"))
                .route("path_route_guoji",
                        r -> r.path("/guoji")           // 可以调用
                                .uri("http://news.baidu.com/guoji"))
                .route("path_route_timeout",
                        r -> r.path("/payment8001/timeout")   // 可以调用
                                .uri("http://localhost:8001/payment8001/timeout"));
        return routes.build();

    }

}
