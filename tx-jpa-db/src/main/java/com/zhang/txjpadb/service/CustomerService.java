package com.zhang.txjpadb.service;

import com.zhang.txjpadb.dao.CustomerDao;
import com.zhang.txjpadb.domain.Customer;
import com.zhang.txjpadb.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate userJdbcTemplate;

    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    @Transactional
    public void createOrder(Order order) {
//        Customer customer = customerDao.getOne( order.getCid() );
//        customer.setAmount( customer.getAmount() - order.getAmount() );
//        customerDao.save( customer );
        if( order.getTitle().contains( "error1")){
            throw new RuntimeException("error1");
        }
        String sql = "insert into order1(cid,title,amount) values( ? ,? ,? ) ";
        orderJdbcTemplate.update( sql , order.getCid() , order.getTitle(), order.getAmount());
        if( order.getTitle().contains( "error2")){
            throw new RuntimeException("error2");
        }
    }
}
