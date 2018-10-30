package com.xiaojiang.sk.mq;

import com.aliyun.openservices.ons.api.*;
import com.xiaojiang.sk.mq.properties.ConsumerProperties;
import com.xiaojiang.sk.mq.properties.OnsMqProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/10/22 上午10:32
 * @desc :
 */
@Configuration
@ConditionalOnProperty(name = "aliyun.ons.mq.access_key")
@EnableConfigurationProperties(OnsMqProperties.class)
public class OnsMqAutoConfiguration {

    @Autowired
    private OnsMqProperties onsMqProperties;


    @Bean
    public OnsMqService onsMqService() {
        return new OnsMqService(buildProducer());
    }


    private Producer buildProducer() {
        Properties properties = new Properties();
        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey,onsMqProperties.getAccess_key());
        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, onsMqProperties.getSecret_key());
        //设置发送超时时间，单位毫秒
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, onsMqProperties.getSendMsg_timeout());
        // 设置 TCP 接入域名（此处以公共云生产环境为例）
        properties.put(PropertyKeyConst.ONSAddr,
                onsMqProperties.getOnsaddr());
        Producer producer = ONSFactory.createProducer(properties);
        //调用 start 方法来启动 Producer，只需调用一次即可
        producer.start();
        return producer;
    }

    @Bean
    @ConditionalOnBean(value = MessageListener.class)
    public Consumer consumer() {
        Properties properties = new Properties();
        // 您在控制台创建的 Consumer ID
        properties.put(PropertyKeyConst.ConsumerId, onsMqProperties.getConsumer_id());
        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey, onsMqProperties.getAccess_key());
        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, onsMqProperties.getSecret_key());
        // 设置 TCP 接入域名（此处以公共云生产环境为例）
        properties.put(PropertyKeyConst.ONSAddr, onsMqProperties.getOnsaddr());
        // 订阅方式 (默认集群)
        if (onsMqProperties.getIs_default_model() == null || onsMqProperties.getIs_default_model()) {
            properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
        }else {
            // 广播订阅方式
             properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
        }
        Consumer consumer = ONSFactory.createConsumer(properties);


        for (ConsumerProperties consumerProperties : MessageListenerHandler.getBean()) {
            consumer.subscribe(consumerProperties.getTopic(), consumerProperties.getTag(), consumerProperties.getMessageListener());
        }
        consumer.start();
        return consumer;
    }


}
