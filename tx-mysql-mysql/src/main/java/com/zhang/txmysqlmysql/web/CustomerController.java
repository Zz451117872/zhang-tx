package com.zhang.txmysqlmysql.web;

import com.zhang.txmysqlmysql.domain.Order;
import com.zhang.txmysqlmysql.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder( Order order){
        customerService.createOrder( order );
        return "success";
    }
}
