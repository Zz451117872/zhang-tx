package com.zhang.txjmsdb.service;

import com.zhang.txjmsdb.dao.CustomerDao;
import com.zhang.txjmsdb.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aa on 2018/7/13.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = "customer:msg:new")
    public void create( String name){
        Customer customer = new Customer();
        customer.setAmount( 1000 );
        customer.setName( name );
        customer.setPasswrod( "111" );

        if( name.contains( "error1 ")){
            throw new RuntimeException("error1");
        }

        customerDao.save( customer );
        String reply = " reply-anno : "+ name;
        jmsTemplate.convertAndSend( "customer:msg:reply" , reply );

        if( name.contains( "error2 ")){
            throw new RuntimeException("error2");
        }
    }

    @Transactional
    public void create( Customer customer ){
        customerDao.save( customer );

        if( customer.getName().contains( "error1 ")){
            throw new RuntimeException("error1");
        }

        String reply = " reply-anno : "+ customer.getName() ;
        jmsTemplate.convertAndSend( "customer:msg:reply" , reply );

        if( customer.getName().contains( "error2 ")){
            throw new RuntimeException("error2");
        }
    }
}
