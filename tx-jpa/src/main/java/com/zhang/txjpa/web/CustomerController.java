package com.zhang.txjpa.web;

import com.zhang.txjpa.dao.CustomerDao;
import com.zhang.txjpa.domain.Customer;
import com.zhang.txjpa.service.CustomerServiceAnnotation;
import com.zhang.txjpa.service.CustomerServiceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
