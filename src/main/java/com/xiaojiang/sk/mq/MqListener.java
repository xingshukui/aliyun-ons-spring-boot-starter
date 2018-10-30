package com.xiaojiang.sk.mq;

import java.lang.annotation.*;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/10/22 下午3:06
 * @desc :
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MqListener {

    /**
     * topic
     * @return
     */
    String topic();

    /**
     * tag
     * @return
     */
    String tag();

}
