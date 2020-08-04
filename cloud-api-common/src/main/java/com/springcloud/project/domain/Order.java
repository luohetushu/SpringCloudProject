package com.springcloud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private long id;

    private long userId;

    private long productId;

    private int count;

    private BigDecimal money;

    private int status;   // 订单状态：0、创建中；1、已完成

}
