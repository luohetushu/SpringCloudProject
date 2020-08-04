package com.springcloud.project.service;

import com.springcloud.project.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {

    //新建订单
    int createOrder(@Param("order") Order order);

}
