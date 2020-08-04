package com.springcloud.project.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

/**
 * 全局日志过滤器
 */
@Component
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(MyLogGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.debug("********进入 MyLogGatewayFilter：" + ZonedDateTime.now());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){   // uname 为空 或者 unmae 请求参数不存在都会进入该条件内
            logger.debug("********uname 参数不存在或 uname 为空********");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
