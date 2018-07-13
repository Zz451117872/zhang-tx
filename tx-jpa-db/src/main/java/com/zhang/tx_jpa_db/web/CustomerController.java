package com.zhang.tx_jpa_db.web;

import com.zhang.tx_jpa_db.domain.Order;
import com.zhang.tx_jpa_db.service.CustomerService;
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
