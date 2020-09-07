package com.liao.service.impl;

import com.liao.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

// 定义消息推送的管道
@EnableBinding(Source.class)
public class MessageProviderImpl implements MessageProvider {

    // 消息推送管道
    @Autowired
    private MessageChannel output;

    /**
     * 消息发送实现类
     * @return
     */
    @Override
    public String send() {
        // 流水号
        String serial = UUID.randomUUID().toString();
        // 将流水号发送出去
        output.send(MessageBuilder.withPayload(serial).build());
        return serial;
    }
}