package com.springcloud.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT-SERVICE", fallback = HystrixHandlerService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment8001/success/{id}")
    public String paymentSuccess(@PathVariable("id") Integer id);

    @GetMapping("/payment8001/timeout/{id}")
    public String paymentTimeOut(@PathVariable("id") Integer id);

}
