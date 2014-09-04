package com.jeefix.home.ledino.web.controller.ui;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BootstrapController {
  private static final Logger log = Logger.getLogger(BootstrapController.class);

  @RequestMapping("/")
  public String redirectToDashboard() {

    return "redirect:/web/";
  }

  @RequestMapping("/web/")
  public String loadDashboard(Model model) {
    log.info("Loading page...");
    return "html/template";
  }
}
