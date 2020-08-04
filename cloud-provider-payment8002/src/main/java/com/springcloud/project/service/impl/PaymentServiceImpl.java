package com.springcloud.project.service.impl;

import com.springcloud.project.dao.PaymentDao;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * @Resource : 也能依赖注入
     */
    @Resource
    PaymentDao paymentDao;

    @Override
    public int insertPayment(PaymentEntity payment) {
        return paymentDao.insertPayment(payment);
    }

    @Override
    public PaymentEntity getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
