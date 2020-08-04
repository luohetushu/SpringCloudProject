package com.springcloud.project.service;

import com.springcloud.project.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
@FeignClient(value = "cloud-seata-service-account2003")    // 账户微服务应用名
public interface AccountService {

    /**
     * 远程调用账户微服务，减少对应用户的额度
     * @param userId 用户 id
     * @param reduceMoney 对应的用户额度减少量
     * @return
     */
    @PostMapping("/account/reduce")
    CommonResult reduceMoney(@RequestParam("userId") long userId, @RequestParam("reduceMoney") BigDecimal reduceMoney);

}
