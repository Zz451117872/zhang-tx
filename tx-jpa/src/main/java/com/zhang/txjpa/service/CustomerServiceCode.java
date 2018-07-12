package com.zhang.txjpa.service;

import com.zhang.txjpa.dao.CustomerDao;
import com.zhang.txjpa.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer add (Customer customer ){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel( TransactionDefinition.ISOLATION_DEFAULT);
        definition.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus ts = transactionManager.getTransaction( definition );
        try{
            customer = customerDao.save( customer );
            if( customer.getName().contains( "error")){
                throw new RuntimeException("error");
            }
            transactionManager.commit( ts );
            return customer.getCid();
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback( ts );
            return null;
        }
    }
}
