package com.zhang.txjms.web;

import com.zhang.txjms.JmsService.CustomerJmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CustomerJmsService customerJmsService;

    @RequestMapping("/annotationJmsMessage")
    public void jmsMessage( String msg){
        String newMsg = " jms : " + msg;
        jmsTemplate.convertAndSend("customer:msg1:new", newMsg );
    }

    @RequestMapping("/annotationMessage")
    public void annotationMessage( String msg ){
        String newMsg = " code : " + msg;
        customerJmsService.handler( newMsg );
    }

    @RequestMapping("/codeJmsMessage")
    public void codeJmsMessage( String msg){
        String newMsg = " jms : " + msg;
        jmsTemplate.convertAndSend("customer:msg2:new", newMsg );
    }

    @RequestMapping("/codeMessage")
    public void codeMessage( String msg ){
        String newMsg = " code : " + msg;
        customerJmsService.handler( newMsg );
    }

    @RequestMapping("/readMsg")
    @ResponseBody
    public String readMsg(){
        jmsTemplate.setReceiveTimeout( 2000);
        Object obj = jmsTemplate.receiveAndConvert("customer:msg:reply");
        return String.valueOf( obj );
    }
}
