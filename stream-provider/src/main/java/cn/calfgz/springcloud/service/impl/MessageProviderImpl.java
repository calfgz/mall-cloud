package cn.calfgz.springcloud.service.impl;

import cn.calfgz.springcloud.service.MessageProvider;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-13 11:36
 */
//定义消息的推送管道
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String uuid = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(uuid).build());
        log.info("send message uuid:{}", uuid);
        return uuid;
    }





}
