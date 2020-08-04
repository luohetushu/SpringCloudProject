package com.springcloud.project.controll;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.loadbalance.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order80")
public class OrderController {

    //public static final String PAYMENT8001_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD.PROVIDER.PAYMENT-SERVICE";  // 使用注册列表中服务提供者的别名

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment8001/insert")
    public CommonResult<PaymentEntity> insertPayment8001(PaymentEntity payment){
        logger.debug("PaymentController：插入数据：{}", payment);
        return restTemplate.postForObject(PAYMENT_URL + "/payment8001/insert", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment8001/entity/insert")
    public CommonResult<PaymentEntity> insertPayment8001ForEntity(PaymentEntity payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment8001/insert",
                payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            // entity.getHeaders() // 响应头
            return entity.getBody();  // 响应体
        } else {
            return new CommonResult<>(444, "数据添加失败");
        }
    }

    @GetMapping("/consumer/payment8001/query/{id}")
    public CommonResult<PaymentEntity> getPayment8001ById(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment8001/query/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment8001/entity/query/{id}")
    public CommonResult<PaymentEntity> getPayment8001ByIdForEntity(@PathVariable("id") long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment8001/query/" + id,
                CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            // entity.getHeaders() // 响应头
            return entity.getBody();  // 响应体
        } else {
            return new CommonResult<>(444, "数据添加失败");
        }
    }

    /************** 服务发现 ********************/
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/discovery", produces = "application/json")
    public DiscoveryClient discovery(){

        // 获取所有的服务名
        List<String> services = discoveryClient.getServices();
        for (String service: services){
            logger.debug("注册中心服务：{}", service);
        }

        // 查询指定服务名
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD.PROVIDER.PAYMENT-SERVICE");
        for (ServiceInstance instance: instances){
            logger.debug(instance.getServiceId() + "\t" + instance.getHost() + "\t"
                    + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }


    /************** 自定义负载均衡 ********************/
    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment8001/lb/port")
    public String getPaymentPortByLB(){
        // 查询指定服务名
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD.PROVIDER.PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return "没有可用的微服务集群";
        }
        URI uri = loadBalancer.instance(instances).getUri();
        logger.debug("服务地址：{}", uri.toString());
        return restTemplate.getForObject(uri + "/payment8001/lb/port", String.class);
    }

    /************** zipkin + sleuth ********************/
    @GetMapping("/consumer/payment8001/zipkin")
    public String getPaymentZipkin(){
        return restTemplate.getForObject(PAYMENT_URL + "/payment8001/zipkin", String.class);
    }


}
