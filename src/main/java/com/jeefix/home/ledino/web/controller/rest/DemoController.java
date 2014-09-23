package com.jeefix.home.ledino.web.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/demo/")
public class DemoController {
    
    @RequestMapping("/rest/demo/")
    @ResponseBody
    public String startDemo() {
        return "OK";
    }
    
}
