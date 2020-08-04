package com.springcloud.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order80")
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud.provider.payment-service";

    Logger logger = LoggerFactory.getLogger(OrderZkController.class);

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/payment/zookeeper")
    public String paymentZookeeper(){
        return restTemplate.getForObject(INVOKE_URL + "/payment8004/zookeeper", String.class);
    }

    // 服务发现
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/discovery")
    public DiscoveryClient discovery(){

        // 获取所有的服务名
        List<String> services = discoveryClient.getServices();
        for (String service: services){
            logger.debug("注册中心服务：{}", service);
        }

        // 查询指定服务名
        List<ServiceInstance> instances = discoveryClient.getInstances("17057edb-8de9-412f-8f59-0ae474b62cb7");
        logger.debug("注册中心服务-实例：{}", instances.size());  // 0
        for (ServiceInstance instance: instances){
            logger.debug(instance.getServiceId() + "\t" + instance.getHost() + "\t"
                    + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

}
