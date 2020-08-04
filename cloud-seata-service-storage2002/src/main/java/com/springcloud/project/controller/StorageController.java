package com.springcloud.project.controller;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 库存微服务，减少相应库存量
     * @param productId 产品 id
     * @param reduceCount 相应的库存减少量
     * @return
     */
    @PostMapping("/reduce")
    public CommonResult reduceStorage(@RequestParam("productId") long productId,
                                      @RequestParam("reduceCount") int reduceCount){
        storageService.reduceCount(productId, reduceCount);
        return new CommonResult(200, "库存微服务: 完成减少 productId: " + productId
                + ", 相应库存量, reduceCount: " + reduceCount);
    }

}
