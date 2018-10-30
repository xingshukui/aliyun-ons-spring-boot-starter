package com.xiaojiang.sk.mq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/10/22 上午10:33
 * @desc :
 */
@ConfigurationProperties(prefix = "aliyun.ons.mq")
public class OnsMqProperties implements Serializable {

    private  String access_key;
    private  String secret_key;
    private String sendMsg_timeout;
    private String consumer_id;
    /**
     * ONSADDR 请根据不同Region进行配置
     * 公网测试: http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
     * 公有云生产: http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
     * 杭州金融云: http://jbponsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
     * 深圳金融云: http://mq4finance-sz.addr.aliyun.com:8080/rocketmq/nsaddr4client-internal
     */
    private  String onsaddr;

    /**
     * 消息订阅方式 默认集群 true:集群 false:广播
     */
    private Boolean is_default_model;



    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getOnsaddr() {
        return onsaddr;
    }

    public void setOnsaddr(String onsaddr) {
        this.onsaddr = onsaddr;
    }

    public String getSendMsg_timeout() {
        return sendMsg_timeout;
    }

    public void setSendMsg_timeout(String sendMsg_timeout) {
        this.sendMsg_timeout = sendMsg_timeout;
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }

    public Boolean getIs_default_model() {
        return is_default_model;
    }

    public void setIs_default_model(Boolean is_default_model) {
        this.is_default_model = is_default_model;
    }
}
