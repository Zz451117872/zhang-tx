package com.zhang.jtajmsjta.service;

import com.zhang.jtajmsjta.dao.CustomerDao;
import com.zhang.jtajmsjta.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomerServiceCode {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JmsTemplate jmsTemplate;

    public Integer add (Customer customer ){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel( TransactionDefinition.ISOLATION_DEFAULT);
        definition.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus ts = transactionManager.getTransaction( definition );
        try{
            customerDao.save( customer );
            if( customer.getName().contains( "error")){
                throw new RuntimeException("error");
            }
            jmsTemplate.convertAndSend( "customer:msg:reply", customer.getName());
            transactionManager.commit( ts );
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback( ts );
            return 0;
        }
    }

    @JmsListener(destination = "customer:msg2:new")
    public void addListener( String name){

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel( TransactionDefinition.ISOLATION_DEFAULT);
        definition.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus ts = transactionManager.getTransaction( definition );
        Customer customer = new Customer();
        try{

            customer.setAmount(123);
            customer.setPassword("xxx");
            customer.setName( name );
            customerDao.save( customer );
            if( name.contains( "error")){
                throw new RuntimeException("error");
            }
            jmsTemplate.convertAndSend( "customer:msg:reply", customer.getName());
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback( ts );
        }
    }
}
