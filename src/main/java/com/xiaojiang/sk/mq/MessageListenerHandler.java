package com.xiaojiang.sk.mq;

import com.aliyun.openservices.ons.api.MessageListener;
import com.xiaojiang.sk.mq.properties.ConsumerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/10/22 下午3:55
 * @desc :
 */
@Component
public class MessageListenerHandler implements ApplicationContextAware {

    private static Environment environment;

    private static ApplicationContext applicationContext;
    private static final String prefix = "${";
    private static final String suffix = "}";
    private static final String nullStr = "";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        MessageListenerHandler.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     *
     * @return List
     * @throws BeansException
     */
    public static List<ConsumerProperties> getBean() throws BeansException {
        List<ConsumerProperties> list = new ArrayList<>();
        try {
            String[] beans = getApplicationContext().getBeanNamesForAnnotation(MqListener.class);
            for (String beanName : beans) {
                Class clazz = applicationContext.getType(beanName);
                MqListener mqListener = AnnotationUtils.findAnnotation(clazz, MqListener.class);
                ConsumerProperties properties = new ConsumerProperties();
                properties.setTopic(environment.getProperty(replace(mqListener.topic())));
                properties.setTag(environment.getProperty(replace(mqListener.tag())));
                properties.setMessageListener((MessageListener) applicationContext.getBean(beanName));
                list.add(properties);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String replace(String val) {
        return val.replace(prefix, nullStr).replace(suffix, nullStr);
    }

    @Autowired
    public MessageListenerHandler(Environment environment) {
        MessageListenerHandler.environment = environment;
    }
}
