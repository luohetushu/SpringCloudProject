package com.springcloud.project.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.project.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order80")
@DefaultProperties(defaultFallback = "defaultPaymentHandler")
public class OrderFeignHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/success/{id}")
    public String paymentSuccess(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentSuccess(id);
    }

    @GetMapping("/consumer/payment/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
//            // 当前线程处理该请求的峰值时间，该时间内正常运行，超过该时间进行兜底方法调用
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentTimeOut(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentTimeOut(id);
    }

    /**
     * 作为超时或者出错的请求方法的服务降级处理后的 fallback 方法
     * @param id
     * @return
     */
    public String paymentTimeoutHandler(@PathVariable("id") Integer id){
        return "我是消费者，对方服务超时或者我自己内部请求方法出错了，进行 fallback，请稍后或检查。。。id：" + id + "\t哭～～～";
    }


    /**
     * 定义通用的兜底方法
     */
    public String defaultPaymentHandler(){
        return "我是通用的兜底处理方法";
    }

}
