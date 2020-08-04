package com.springcloud.project.controller;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.service.PaymentFeignService;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order80")
public class OrderFeignController {

    Logger logger = LoggerFactory.getLogger(OrderFeignController.class);

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment8001/insert")
    public CommonResult insertPayment8001(PaymentEntity payment){
        logger.debug("PaymentController：插入数据：{}", payment);
        return paymentFeignService.insertPayment(payment);
    }

    @GetMapping("/consumer/payment8001/query/{id}")
    public CommonResult<PaymentEntity> getPayment8001ById(@PathVariable("id") long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment8001/timeout")
    public String getPortWhenTimeout(){
        return paymentFeignService.getPortWhenTimeout();
    }


}
