package com.springcloud.project;

import com.springcloud.project.config.DataSourceProxyConfig;
import com.springcloud.project.ignore.IgnoreScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = DataSourceProxyConfig.class))
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
//        classes = {IgnoreScan.class}))
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderMain2001 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain2001.class, args);
    }
}
