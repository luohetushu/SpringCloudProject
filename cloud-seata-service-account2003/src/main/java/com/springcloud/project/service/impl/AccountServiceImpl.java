package com.springcloud.project.service.impl;

import com.springcloud.project.dao.AccountDao;
import com.springcloud.project.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public int reduceMoney(long userId, BigDecimal reduceMoney) {

        logger.debug("账户微服务：减少用户额度～～");
        int result = accountDao.reduceMoney(userId, reduceMoney);
        logger.debug("账户微服务：完成减少用户额度～～");

        return result;
    }
}
