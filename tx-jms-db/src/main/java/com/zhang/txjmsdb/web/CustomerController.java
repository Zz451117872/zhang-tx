package com.zhang.txjmsdb.web;

import com.zhang.txjmsdb.domain.Customer;
import com.zhang.txjmsdb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by aa on 2018/7/13.
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/msg/create")
    public void create( String msg ){
        jmsTemplate.convertAndSend( "customer:msg:new" ,msg);
    }

    @RequestMapping("/create")
    public void create(Customer customer){
        customerService.create( customer );
    }

    @RequestMapping("/read")
    public String read(){
        jmsTemplate.setReceiveTimeout( 2000 );
        Object obj = jmsTemplate.receiveAndConvert( "customer:msg:reply");
        return String.valueOf( obj );
    }
}
