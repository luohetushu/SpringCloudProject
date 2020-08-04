package com.springcloud.project.dao;

import com.springcloud.project.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

@Mapper
public interface OrderDao {

    //新建订单
    int createOrder(@Param("order") Order order);

    // 订单状态：0、创建中；1、已完成
    // 更新订单状态，0 -> 1
    int updateOrderStatus(@Param("userId") long userId, @Param("productId") long productId, @Param("status") int status);

}
