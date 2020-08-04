package com.springcloud.project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/client3355")
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;    // 自己配置文件中的配置内容

    @Value("${config.info}")
    private String configInfo;   // 这个是用来读取配置中心里配置文件里的配置内容

    @GetMapping("/config-info")
    public String getConfigInfo(){
        return "serverPort: " + serverPort + "\tconfigInfo: " + configInfo;
    }

}
