package com.zhang.tx_jpa_db.service;

import com.zhang.tx_jpa_db.dao.CustomerDao;
import com.zhang.tx_jpa_db.domain.Customer;
import com.zhang.tx_jpa_db.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    @Transactional
    public void createOrder(Order order) {
        Customer customer = customerDao.getOne( order.getCid() );
        customer.setAmount( customer.getAmount() - order.getAmount() );
        customerDao.save( customer );
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
