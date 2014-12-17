package com.jeefix.home.ledino.web.controller.rest;

import java.util.Map;

import com.jeefix.home.ledino.web.controller.rest.model.ResponseWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeefix.home.ledino.logic.animation.AnimationService;

@Controller
@RequestMapping("/rest/animation/")
public class AnimationController {
    
    private static final Logger log = Logger.getLogger(AnimationController.class);
    @Autowired
    private AnimationService animationService;
    
    @RequestMapping("{animationName}/start")
    @ResponseBody
    public ResponseWrapper startAnimation(@PathVariable("animationName") String animationName, @RequestParam Map<String, String> model) {
        log.info(String.format("Starting animation %s with parameters: %s",animationName,model));
        animationService.startAnimation(animationName, model);
        return ResponseWrapper.OK;
    }


    @RequestMapping("/stop")
    @ResponseBody
    public ResponseWrapper stopCurrentAnimation() {
        log.info("Stopping animations");
        animationService.stopCurrentAnimation();
        return ResponseWrapper.OK;
    }
    

    
}
