package com.twilio.sampletemplatespring.controllers;

import java.util.Collections;
import java.util.Map;

import com.twilio.sampletemplatespring.models.SampleResponse;
import com.twilio.sampletemplatespring.models.SmsMessage;
import com.twilio.sampletemplatespring.services.TwilioClientWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SampleController {

  private final TwilioClientWrapper twilioClientWrapper;

  Logger logger = LoggerFactory.getLogger(SampleController.class);

  public SampleController(TwilioClientWrapper twilioClientWrapper) {
    this.twilioClientWrapper = twilioClientWrapper;
  }

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

  @PostMapping("/send-sms")
  @ResponseBody
  public ResponseEntity<SampleResponse> sendSMS(@RequestBody SmsMessage input) {
    try {
      String responseMessage = new StringBuilder()
          .append("SMS sent to ")
          .append(input.getRecipient())
          .append(". ")
          .append("Message SID: ")
          .append(twilioClientWrapper.sendMessage(input))
          .toString();
  
      SampleResponse sampleResponse = new SampleResponse("success", responseMessage);
      return new ResponseEntity<SampleResponse>(sampleResponse, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unexpected Exception", e);
      SampleResponse sampleResponse = new SampleResponse("error", "Failed to send SMS. Check server logs for more details.");
      return new ResponseEntity<SampleResponse>(sampleResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
