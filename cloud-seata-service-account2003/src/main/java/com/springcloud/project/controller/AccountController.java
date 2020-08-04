package com.springcloud.project.controller;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/reduce")
    public CommonResult reduceMoney(@RequestParam("userId") long userId,
                                    @RequestParam("reduceMoney") BigDecimal reduceMoney){
        accountService.reduceMoney(userId, reduceMoney);
        return new CommonResult(200, "账户微服务：完成减少 userId: " + userId
                + ", 相应额度, reduceMoney: " + reduceMoney);
    }

}
