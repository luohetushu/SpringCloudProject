package com.springcloud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    /**
     * id BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
     * 	product_id BIGINT(11) DEFAULT NULL COMMENT '产品id',
     * 	total INT(11) DEFAULT NULL COMMENT '总库存',
     * 	used INT(11) DEFAULT NULL COMMENT '已用库存',
     * 	residue INT(11) DEFAULT NULL COMMENT '剩余库存'
     */
    private long id;

    //产品 id
    private long productId;

    // 产品总库存
    private int total;

    // 产品已使用数量
    private int used;

    // 产品剩余库存
    private int residue;

}
