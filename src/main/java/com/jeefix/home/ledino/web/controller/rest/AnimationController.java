package com.jeefix.home.ledino.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeefix.home.ledino.logic.demo.AnimationService;

@Controller
@RequestMapping("/rest/animation/")
public class AnimationController {
    
    @Autowired
    private AnimationService animationService;
    
    @RequestMapping("{animationName}/start")
    @ResponseBody
    public String startAnimation(@PathVariable("animationName") String animationName) {
        animationService.startAnimation(animationName);
        return "200";
    }
    
    @RequestMapping("/stop")
    @ResponseBody
    public String stopAll() {
        animationService.stopAllAnimations();
        return "200";
    }
    
}
