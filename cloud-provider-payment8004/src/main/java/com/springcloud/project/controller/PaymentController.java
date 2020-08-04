package com.springcloud.project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment8004")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/zookeeper")
    public String paymentZookeeper(){
        return "Spring Cloud With Zookeeper: " + serverPort + "\t" + UUID.randomUUID().toString();
    }

}
