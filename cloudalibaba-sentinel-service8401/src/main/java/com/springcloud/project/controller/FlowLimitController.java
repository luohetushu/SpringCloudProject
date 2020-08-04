package com.springcloud.project.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "服务限流～～～～testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "服务限流～～～～testB";
    }

    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试降级规则之RT～～～～testD";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", fallback = "testHotKeyHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "测试热点规则，并使用自定义熔断兜底方法，p1 = " + p1 + "\tp2 = " + p2;
    }

    //自定义的熔断兜底方法
    public String testHotKeyHandler(String p1, String p2){
        // 发生熔断时，Sentinel 系统默认提示：Blocked by Sentinel (flow limiting)
        return "这是测试热点规则发生熔断时的兜底方法，p1 = " + p1 + "\tp2 = " + p2;
    }

}
