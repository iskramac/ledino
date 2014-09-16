package com.jeefix.home.ledino.web.controller.ui;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Serves UI template
 * 
 * @author mais
 *
 */
@Controller
public class UserInterfaceController {
  private static final Logger log = Logger.getLogger(UserInterfaceController.class);

  @RequestMapping("/")
  public String redirectToDashboard() {

    return "redirect:/ui/";
  }

  @RequestMapping("/ui/")
  public String loadDashboard(HttpServletRequest request) {
    log.info(String.format("Loading Ledino web application for client with IP address %s", request.getRemoteAddr()));
    return "template";
  }
}
