package com.springcloud.project.controller;

import com.springcloud.project.PaymentHystrixMain8001;
import com.springcloud.project.service.PaymentHystrixService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment8001")
public class PaymentHystrixController {

    Logger logger = LoggerFactory.getLogger(PaymentHystrixMain8001.class);

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/success/{id}")
    public String paymentSuccess(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentSuccess(id);
        logger.debug("******成功结果：{}", result);
        return result;
    }

    @GetMapping("/timeout/{id}")
    public String paymentTimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentTimeOut(id);
        logger.debug("******超时结果：{}", result);
        return result;
    }

    /*****************服务熔断：当失败的调用达到一定的阈值（默认是5秒内20次调用中50%以上调用失败），启动熔断机制************************/
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        logger.debug("******超时结果：{}", result);
        return result;
    }

}
