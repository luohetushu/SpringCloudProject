package com.springcloud.project.service;

import com.springcloud.project.entities.PaymentEntity;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int insertPayment(@Param("payment") PaymentEntity payment);

    public PaymentEntity getPaymentById(@Param("id") long id);
}
