package com.springcloud.project.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;

/**
 * 自定义触发 Sentinel 规则处理方法
 */
public class MyBlockHandler {

    public static CommonResult globalBlockHandler1(PaymentEntity payment,
                                                   BlockException exception){
        return new CommonResult<>(444, exception.getClass().getCanonicalName()
                + " 服务不可用, 1 号方法，id = " + payment.getId(), null);
    }

    public static CommonResult globalBlockHandler2(PaymentEntity payment,
                                                   BlockException exception){
        return new CommonResult<>(444, exception.getClass().getCanonicalName()
                + " 服务不可用, 2 号方法，id = " + payment.getId(), null);
    }

}
