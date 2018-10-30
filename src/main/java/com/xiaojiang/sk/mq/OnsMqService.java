package com.xiaojiang.sk.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/10/22 上午10:40
 * @desc :
 */
public class OnsMqService {

    private static final Logger logger = LoggerFactory.getLogger(OnsMqService.class);
    private Producer producer;

    public OnsMqService(Producer producer) {
        this.producer = producer;
    }

    /**
     * 发送消息
     * @param message
     * @return
     */
    public boolean sendMsg(Message message) {

        SendResult sendResult;
        try {
            sendResult = producer.send(message);
            if (sendResult != null) {
                logger.info(" Send mq message success. Topic is:" + message.getTopic() + " msgId is: " + sendResult.getMessageId());
            }

        }catch (Exception e) {
            logger.error(" Send mq message failed. Topic is:" + message.getTopic());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
