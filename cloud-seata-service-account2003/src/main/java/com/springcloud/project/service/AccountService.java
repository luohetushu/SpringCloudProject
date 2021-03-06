package com.springcloud.project.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 减少用户额度
     * @param userId  用户 id
     * @param reduceMoney  减少的金额
     * @return
     */
    int reduceMoney(@Param("userId") long userId, @Param("reduceMoney") BigDecimal reduceMoney);

}
