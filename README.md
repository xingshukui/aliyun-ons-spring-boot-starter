#### tips

##### 项目中添加依赖：把项目clone下来，打包之后引入即可
    
    
#####   添加配置OnsMqProperties，配置如下，注意没有配置的话不会装配bean

    aliyun: 
      ons: 
        mq: 
          access_key: xxxxx
          secret_key: xxxxx
          sendMsg_timeout: xx
          onsaddr: xxxxxx
          consumer_id: xxxx
          is_default_model: true/false(非必须，默认集群模式)

#####   发MQ消息：引入OnsMqService
    @Autowired
    private OnsMqService onsMqService;
            
    下面方法发消息
    onsMqService.sendMsg(Message message)
    
#####  消费者配置：在项目实现的MessageListener类上添加MqListener注解，指定topic和tag就行，这里${配置文件中topic}，支持多个topic/listener。注意这里的实现类必须是bean

        @Component
        @MqListener(topic =  "${xxx}", tag = "${xxx}")
        public class MyMessageListener implements MessageListener
