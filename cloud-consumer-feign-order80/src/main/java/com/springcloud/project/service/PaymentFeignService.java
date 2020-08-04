package com.springcloud.project.service;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "cloud.provider.payment-service")  // 要调用的服务集群名
public interface PaymentFeignService {

    @PostMapping("/payment8001/insert")
    CommonResult insertPayment(@RequestBody PaymentEntity payment);

    @GetMapping("/payment8001/query/{id}")
    CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") long id);

    @GetMapping("/payment8001/timeout")
    String getPortWhenTimeout();

}
