package com.springcloud.project.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class CircleBreakerController {

    @Value(value = "${service-url.nacos-provider-service}")
    private String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/testFallback/{id}", produces = "application/json")
    @SentinelResource("testFallback")
    public CommonResult<PaymentEntity> testFallback(@PathVariable("id") long id){
        CommonResult<PaymentEntity> result = restTemplate.getForObject(serviceUrl + "/payment/get/" + id,
                CommonResult.class);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常。。。");
        } else if (result.getData() == null){
            throw new NullPointerException("NullPointerException，该 id 没有对应记录，空指针异常。。。");
        }
        return result;
    }

    /************ openfeign ****************/
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/payment/get/{id}", produces = "application/json")
    public CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }


}
