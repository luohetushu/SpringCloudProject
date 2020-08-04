package com.springcloud.project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    /**
     * 减少对应产品库存量
     * @param productId  产品 id
     * @param reduceCount  减少的量
     * @return
     */
    int reduceCount(@Param("productId") long productId, @Param("reduceCount") int reduceCount);

}
