package com.springcloud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private long id;

    //用户 id
    private long userId;

    // 用户总额度
    private BigDecimal total;

    // 用户已花费额度
    private BigDecimal used;

    // 用户剩余额度
    private BigDecimal residue;


}
