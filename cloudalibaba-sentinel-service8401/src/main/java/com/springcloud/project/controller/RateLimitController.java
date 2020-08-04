package com.springcloud.project.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.handler.MyBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    /**
     * 使用自定义的兜底方法，但有问题：
     * 1、自定义的处理方法与业务方法耦合在一起，违反低耦合
     * 2、每个业务方法都单独配置一个处理方法的话，代码量膨胀
     * 3、没有体现全局统一的处理方法
     * @param payment
     * @return
     */
    @GetMapping(value = "/testResourceUseHandler", produces = "application/json")
    @SentinelResource(value = "testResourceUseHandler", blockHandler = "testResourceHandler")
    public CommonResult<PaymentEntity> testResourceUseHandler(PaymentEntity payment){
        return new CommonResult<>(200, "按资源名称限流测试 success", payment);
    }

    public CommonResult<?> testResourceHandler(BlockException exception){
        return new CommonResult<>(444, exception.getClass().getCanonicalName() + "\t服务不可用", null);
    }

    /**
     * 此处没有配置自定义兜底方法，触发 Sentinel 规则时使用默认的
     * @return
     */
    @GetMapping(value = "/testResource", produces = "application/json")
    @SentinelResource(value = "testResource")
    public CommonResult<PaymentEntity> testResource(PaymentEntity payment){
        return new CommonResult<>(200, "按资源名称限流测试 success", payment);
    }

    /**
     * 用来测试全局的自定义处理方法
     * @param payment
     * @return
     */
    @GetMapping(value = "/testGlobalHandler", produces = "application/json")
    @SentinelResource(value = "testGlobalHandler",
            blockHandlerClass = MyBlockHandler.class, blockHandler = "globalBlockHandler2")
    public CommonResult testGlobalHandler(PaymentEntity payment){
        return new CommonResult<>(200, "按资源名称限流测试全局的自定义处理方法 success", payment);
    }

}
