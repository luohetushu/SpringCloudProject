package com.springcloud.project.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.springcloud.project.ignore.IgnoreScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 规则组件所在的配置类不能在 @ComponentScan 能扫描到的包下，
 * 否则该配置类会被所有的Ribbon客户端所共享，达不到特殊化定制的目的
 * 使用配置文件进行排除配置
 */
@Configuration
@IgnoreScan
public class MyRuleConfig {

    //用来替换 Robbin 默认的负载均衡规则
    @Bean
    public IRule randomRule(){
        return new RandomRule();
    }

}
