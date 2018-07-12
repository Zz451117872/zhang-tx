package com.zhang.txjms.JmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomerJmsService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional
    @JmsListener(destination = "customer:msg1:new", containerFactory = "msgFactory")
    public void handler(String msg){
        String reply = "reply-annotation : "+ msg;
        jmsTemplate.convertAndSend( "customer:msg:reply" , reply );
        if( msg.contains("error")){
            throw new RuntimeException("error");
        }
    }

    @Transactional
    @JmsListener(destination = "customer:msg2:new", containerFactory = "msgFactory")
    public void code( String msg){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel( TransactionDefinition.ISOLATION_DEFAULT);
        TransactionStatus ts = transactionManager.getTransaction( definition );
        try{
            String reply = " reply-code: "+ msg;
            jmsTemplate.convertAndSend("customer:msg:reply" ,msg);
            if( msg.contains( "error")){
                throw new RuntimeException("error");
            }
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback( ts );
        }
    }
}
