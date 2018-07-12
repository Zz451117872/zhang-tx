package com.zhang.jtajmsjta.web;


import com.zhang.jtajmsjta.dao.CustomerDao;
import com.zhang.jtajmsjta.domain.Customer;
import com.zhang.jtajmsjta.service.CustomerServiceAnnotation;
import com.zhang.jtajmsjta.service.CustomerServiceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerServiceAnnotation serviceAnnotation;

    @Autowired
    private CustomerServiceCode serviceCode;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/addByAnnotation")
    @ResponseBody
    public Integer addByAnnotation(Customer customer ){
        return serviceAnnotation.add( customer );
    }

    @RequestMapping("/addByCode")
    @ResponseBody
    public Integer addByCode( Customer customer ){
        return serviceCode.add( customer );
    }

    @Transactional
    @RequestMapping("/msg/addByAnnotation")
    public void addByAnnotation(String name ){
        jmsTemplate.convertAndSend( "customer:msg1:new" , name );
    }

    @Transactional
    @RequestMapping("/msg/addByCode")
    public void addByCode( String name ){
        jmsTemplate.convertAndSend( "customer:msg2:new" , name );
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<Customer> all(){
        return customerDao.findAll();
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public String deleteAll(){
        customerDao.deleteAllInBatch();
        return "success";
    }

    @Transactional
    @RequestMapping("/msg/read")
    @ResponseBody
    public String read(){
        jmsTemplate.setReceiveTimeout( 2000 );
        Object obj = jmsTemplate.receiveAndConvert( "customer:msg:reply");
        return String.valueOf( obj );
    }
}
