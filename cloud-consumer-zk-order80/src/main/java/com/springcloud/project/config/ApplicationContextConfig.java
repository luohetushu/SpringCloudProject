package com.springcloud.project.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean(name = "restTemplate")
    @LoadBalanced  // 注解赋予 RestTemplate 负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
