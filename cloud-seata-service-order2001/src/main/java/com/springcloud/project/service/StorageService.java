package com.springcloud.project.service;

import com.springcloud.project.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cloud-seata-service-storage2002")    // 库存微服务应用名
public interface StorageService {

    /**
     * 远程调用库存微服务，减少相应库存量
     * @param productId 产品 id
     * @param reduceCount 相应的库存减少量
     * @return
     */
    @PostMapping("/storage/reduce")
    CommonResult reduceStorage(@RequestParam("productId") long productId, @RequestParam("reduceCount") int reduceCount);

}
