package com.springcloud.project.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan(value = "com.springcloud.project.dao")
public class MybatisConfig {
}
