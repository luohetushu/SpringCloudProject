package com.springcloud.project.service.fallback;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService {
    @Override
    public CommonResult<PaymentEntity> getPaymentById(Long id) {
        return new CommonResult<>(444, "发生服务降级时的熔断方法", new PaymentEntity(id, "errSerial"));
    }
}
