package com.zhang.jtajmsjta.service;


import com.zhang.jtajmsjta.dao.CustomerDao;
import com.zhang.jtajmsjta.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class CustomerServiceAnnotation {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public Integer add(Customer customer){
        customer = customerDao.save( customer );
        if( customer.getName().contains( "error1")){
            throw new RuntimeException("error1");
        }
        jmsTemplate.convertAndSend( "customer:msg:reply", customer.getName());
        if( customer.getName().contains( "error2")){
            throw new RuntimeException("error2");
        }
        return customer.getCid();
    }

    @Transactional
    @JmsListener(destination = "customer:msg1:new")
    public void addListener( String name){
        Customer customer = new Customer();
        customer.setAmount(123);
        customer.setPassword("xxx");
        customer.setName( name );
        customerDao.save( customer );
        if( name.contains( "error1")){
            throw new RuntimeException("error1");
        }
        jmsTemplate.convertAndSend( "customer:msg:reply", name);
        if( customer.getName().contains( "error2")){
            throw new RuntimeException("error2");
        }
    }


}
