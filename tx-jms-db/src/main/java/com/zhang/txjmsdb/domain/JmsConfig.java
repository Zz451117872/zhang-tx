package com.zhang.txjmsdb.domain;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * Created by aa on 2018/7/14.
 */
@Configuration
public class JmsConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        TransactionAwareConnectionFactoryProxy proxy = new TransactionAwareConnectionFactoryProxy();
        proxy.setTargetConnectionFactory( factory );
        proxy.setSynchedLocalTransactionAllowed( true );
        return proxy;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setSessionTransacted( true);
        return jmsTemplate;
    }


}
