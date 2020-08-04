package com.springcloud.project.service.impl;

import com.springcloud.project.dao.StorageDao;
import com.springcloud.project.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public int reduceCount(long productId, int reduceCount) {

        logger.debug("库存微服务：减少相应订单库存量～～");
        int result = storageDao.reduceCount(productId, reduceCount);
        logger.debug("库存微服务：完成减少相应订单库存量～～");

        return result;
    }

}
