package com.jeefix.home.ledino.web.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManualModeController {

  @RequestMapping("/web/mode/manual")
  public String loadManualModePage() {
    return "manualmode";
  }
}
