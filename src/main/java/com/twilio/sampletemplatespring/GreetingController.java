package com.twilio.sampletemplatespring;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("title", "Template App");
    return "index";
  }

  @GetMapping("/example")
  @ResponseBody
  public Map<String,Boolean> example() {
    return Collections.singletonMap("example", true);
  }

  @GetMapping("/greeting")
  public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    System.out.println("MODEL: " + model);
    model.addAttribute("name", name);
    return "greeting";
  }
}