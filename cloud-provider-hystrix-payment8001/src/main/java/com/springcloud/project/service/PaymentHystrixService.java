package com.springcloud.project.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentHystrixService {

    public String paymentSuccess(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "\t获取参数id：" + id + "\tSuccess";
    }

    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            // 当前线程处理该请求的峰值时间，该时间内正常运行，超过该时间进行兜底方法调用
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentTimeOut(Integer id){
        int sleepTime = 3;
        try {
            //Thread.sleep(sleepTime);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "\t获取参数id：" + id + "\tTimeout（秒）：" + sleepTime;
    }

    /**
     * 作为超时或者出错的请求方法的服务降级处理后的 fallback 方法
     * @param id
     * @return
     */
    public String paymentTimeoutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "\t超时 fallback，请稍后。。。id：" + id + "\t哭～～～";
    }



    /*****************服务熔断：当失败的调用达到一定的阈值（默认是5秒内20次调用中50%以上调用失败），启动熔断机制************************/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启熔断机制
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),  // 一定时间内的请求次数阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 一定的时间（时间窗口期）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")   // 错误请求次数百分比
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new ArithmeticException("**********id 不能为负数***********");
        }
        String serialNumber = IdUtil.simpleUUID();
        return "线程池：" + Thread.currentThread().getName() + "\t调用成功，获取参数id：" + id;
    }

    public String paymentCircuitBreakerHandler(Integer id){
        return "id 不能为负数，请稍后再试，id: " + id;
    }

}
