package com.springcloud.project.controller;

import com.springcloud.project.domain.Order;
import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public CommonResult createOrder(Order order){
        orderService.createOrder(order);
        return new CommonResult(200, "订单微服务：订单创建成功");
    }

}
