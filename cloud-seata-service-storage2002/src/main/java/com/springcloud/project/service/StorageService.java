package com.springcloud.project.service;

import org.apache.ibatis.annotations.Param;

public interface StorageService {

    /**
     * 减少对应产品库存量
     * @param productId  产品 id
     * @param reduceCount  减少的量
     * @return
     */
    int reduceCount(@Param("productId") long productId, @Param("reduceCount") int reduceCount);

}
