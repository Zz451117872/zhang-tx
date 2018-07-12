package com.zhang.txjta.service;


import com.zhang.txjta.dao.CustomerDao;
import com.zhang.txjta.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceAnnotation {

    @Autowired
    private CustomerDao customerDao;

    @Transactional
    public Integer add(Customer customer){
        customer = customerDao.save( customer );
        if( customer.getName().contains( "error")){
            throw new RuntimeException("error");
        }
        return customer.getCid();
    }
}
