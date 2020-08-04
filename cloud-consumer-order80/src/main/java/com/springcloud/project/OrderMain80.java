package com.springcloud.project;

import com.springcloud.project.ignore.IgnoreScan;
import com.springcloud.project.rule.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
        classes = {IgnoreScan.class}))
@EnableEurekaClient
@EnableDiscoveryClient    // 用于服务发现
//@RibbonClient(name = "CLOUD.PROVIDER.PAYMENT-SERVICE", configuration = MyRuleConfig.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }

}
