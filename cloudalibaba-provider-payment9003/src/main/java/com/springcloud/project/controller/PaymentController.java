package com.springcloud.project.controller;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value(value = "${server.port}")
    private String serverPort;

    public static Map<Long, PaymentEntity> map = new HashMap<>();
    static {
        map.put(1L, new PaymentEntity(10001L, "支付9003序列号1"));
        map.put(2L, new PaymentEntity(10002L, "支付9003序列号2"));
        map.put(3L, new PaymentEntity(10003L, "支付9003序列号3"));
    }


    @GetMapping("/get/{id}")
    public CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") Long id){
        PaymentEntity payment = map.get(id);
        return new CommonResult<>(200, "查询成功，支付9003, port: " + serverPort, payment);
    }

}
