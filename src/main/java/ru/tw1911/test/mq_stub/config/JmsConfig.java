package ru.tw1911.test.mq_stub.config;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfig {

    Logger logger = LoggerFactory.getLogger(JmsConfig.class);

    @Value("${ibm.mq.host}")
    private String host;

    @Value("${ibm.mq.port}")
    private int port;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.queueManager}")
    private String queueManager;

    @Value("${ibm.mq.user}")
    private String user;

    @Value("${ibm.mq.password}")
    private String password;

//    @Bean
//    public MQQueueConnectionFactory mqQueueConnectionFactory()  {
//
//        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
//        mqQueueConnectionFactory.setHostName(host);
//        try {
//            mqQueueConnectionFactory.setTargetClientMatching(true);
//            mqQueueConnectionFactory.setCCSID(1208);
//            mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
//            mqQueueConnectionFactory.setChannel(channel);
//            mqQueueConnectionFactory.setPort(port);
//            mqQueueConnectionFactory.setQueueManager(queueManager);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getStackTrace().toString());
//            logger.error("MqQueueConnectionFactory ", e);
//        }
//        return mqQueueConnectionFactory;
//    }
//
//    @Bean
//    UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter(
//            MQQueueConnectionFactory mqQueueConnectionFactory) {
//        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
//        userCredentialsConnectionFactoryAdapter.setUsername(user);
//        userCredentialsConnectionFactoryAdapter.setPassword(password);
//        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(mqQueueConnectionFactory);
//        return userCredentialsConnectionFactoryAdapter;
//    }
//
//    @Bean
//    @Primary
//    public CachingConnectionFactory cachingConnectionFactory(
//            UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter) {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setTargetConnectionFactory(userCredentialsConnectionFactoryAdapter);
//        cachingConnectionFactory.setSessionCacheSize(500);
//        cachingConnectionFactory.setReconnectOnException(true);
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public PlatformTransactionManager jmsTransactionManager(CachingConnectionFactory cachingConnectionFactory) {
//        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
//        jmsTransactionManager.setConnectionFactory(cachingConnectionFactory);
//        return jmsTransactionManager;
//    }
//
//    @Bean
//    public JmsOperations jmsOperations(CachingConnectionFactory cachingConnectionFactory) {
//        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
//        jmsTemplate.setReceiveTimeout(500);
//        return jmsTemplate;
//    }
//
//    @Bean
//    public JmsListenerContainerFactory<?> myFactory(CachingConnectionFactory cachingConnectionFactory,
//                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        configurer.configure(factory, cachingConnectionFactory);
//        return factory;
//    }

//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        converter.setObjectMapper(getObjectMapper());
//        return converter;
//    }
    public static final String IBM_MQ_CONTAINER_FACTORY_NAME = "IbmContainerFactory";

    @Bean(name = IBM_MQ_CONTAINER_FACTORY_NAME)
    public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory,DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        return factory;
    }
}
