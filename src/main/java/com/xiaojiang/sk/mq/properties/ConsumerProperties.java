package com.xiaojiang.sk.mq.properties;

import com.aliyun.openservices.ons.api.MessageListener;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/10/22 下午3:24
 * @desc :
 */
public class ConsumerProperties {
    private String topic;
    private String tag;
    private MessageListener messageListener;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
