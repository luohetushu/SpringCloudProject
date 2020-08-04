package com.springcloud.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class StreamConsumerMessageListenerController {

    Logger logger = LoggerFactory.getLogger(StreamConsumerMessageListenerController.class);

    @Value(value = "${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        logger.debug("我是消费者2号，port：{}\t接收到的消息体为：{}，消息头为：{}", serverPort, message.getPayload(),
                message.getHeaders());
    }

}
