package com.springcloud.project.service.impl;

import com.springcloud.project.dao.OrderDao;
import com.springcloud.project.domain.Order;
import com.springcloud.project.service.AccountService;
import com.springcloud.project.service.OrderService;
import com.springcloud.project.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;   // 用于远程调用库存微服务

    @Resource
    private AccountService accountService;   // 用于远程调用账户微服务

    /**
     * 创建订单 -> 远程调用库存微服务，减少相应库存量 -> 远程调用账户微服务，减少对应用户的额度 -> 修改订单状态，0 -> 1
     * @param order
     * @return
     */
    @Override
    @GlobalTransactional(name = "order_create_tx", rollbackFor = Exception.class)
    public int createOrder(Order order) {

        logger.debug("****************订单微服务：创建订单～～");
        int result = orderDao.createOrder(order);

        logger.debug("****************订单微服务：创建订单结果，result: {}", result);
        // 订单创建成功的话，库存与账户 数据库内容需要做相应的改变
        if (result > 0){

            // todo 远程调用库存微服务，减少相应库存量
            logger.debug("****************订单微服务：远程调用库存微服务，减少相应库存量～～");
            storageService.reduceStorage(order.getProductId(), order.getCount());
            logger.debug("****************订单微服务：远程调用库存微服务，完成相应库存量减少～～");

            // todo 远程调用账户微服务，减少相应额度
            logger.debug("****************订单微服务：远程调用账户微服务，减少对应用户的额度～～");
            accountService.reduceMoney(order.getUserId(), order.getMoney());
            logger.debug("****************订单微服务：远程调用账户微服务，完成对应用户额度的减少～～");

            // 修改订单状态，0 -> 1 表示完成订单创建
            logger.debug("****************订单微服务：修改订单状态，0 -> 1～～");
            orderDao.updateOrderStatus(order.getUserId(), order.getProductId(), 0);
            logger.debug("****************订单微服务：完成修改订单状态，0 -> 1～～");

        }

        return result;
    }
}
