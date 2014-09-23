package com.jeefix.home.ledino.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    
    @Autowired
    private ArduinoService service;
    
}
