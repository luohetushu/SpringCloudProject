package com.springcloud.project.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.IdUtil;
import com.springcloud.project.service.IMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(Source.class)    // 定义消息的发送管道
public class MessageProviderImpl implements IMessageProvider {

    Logger logger = LoggerFactory.getLogger(MessageProviderImpl.class);

    @Resource
    private MessageChannel output;  // 消息发送管道

    @Override
    public String send() {
        String serial = IdUtil.simpleUUID();
        // 发送消息
        output.send(MessageBuilder.withPayload(serial).build());
        logger.debug("发送的消息序列号：{}", serial);
        return "消息已发送";
    }

}
