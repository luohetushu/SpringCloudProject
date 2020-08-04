package com.springcloud.project.service;

import org.springframework.stereotype.Component;

@Component
public class HystrixHandlerService implements PaymentHystrixService {
    @Override
    public String paymentSuccess(Integer id) {
        return "我是成功方法的兜底方法，当对方服务超时或宕机，或双方出错，我就会被触发，id = " + id;
    }

    @Override
    public String paymentTimeOut(Integer id) {
        return "我是超时方法的兜底方法，当对方服务超时或宕机，或双方出错，我就会被触发，id = " + id;
    }
}
