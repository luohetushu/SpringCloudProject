package com.springcloud.project.dao;

import com.springcloud.project.entities.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    public int insertPayment(@Param("payment") PaymentEntity payment);

    public PaymentEntity getPaymentById(@Param("id") long id);

}
