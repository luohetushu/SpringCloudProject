package com.springcloud.project.controller;

import com.springcloud.project.entities.CommonResult;
import com.springcloud.project.entities.PaymentEntity;
import com.springcloud.project.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment8001")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * todo Content-Type →application/json 如果请求头设置这个，则需要添加 @RequestBody
     * @param payment
     * @return
     */
    @PostMapping("/insert")
    public CommonResult insertPayment(@RequestBody PaymentEntity payment){
        logger.debug("PaymentController：插入数据：{}", payment);
        int result = 0;
        if (payment != null) {
            result = paymentService.insertPayment(payment);
            logger.debug("PaymentController：插入结果：{}", result);
        } else {
            return new CommonResult(444, "插入的数据为空", null);
        }
        if (result > 0){
            return new CommonResult(200, "插入数据成功，端口号为 port = " + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败", payment);
        }
    }

    @GetMapping("/query/{id}")
    public CommonResult<PaymentEntity> getPaymentById(@PathVariable("id") long id){
        PaymentEntity payment = paymentService.getPaymentById(id);
        logger.debug("PaymentController：查询结果：{}", payment);
        if (payment != null){
            return new CommonResult<>(200, "查询成功，端口号为 port = " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录，查询失败，id = " + id, null);
        }
    }

    @GetMapping("/lb/port")
    public String getServerPortByLB(){
        return serverPort;
    }

    @GetMapping("/timeout")
    public String getPortWhenTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin(){
        return "server port: " + serverPort + "\twelcome to use zipkin~~";
    }

}
