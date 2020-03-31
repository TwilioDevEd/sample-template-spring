package com.twilio.sampletemplatespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleTemplateSpringApplication {

  public static void main(String[] args) {
    SpringApplication application = 
      new SpringApplication(SampleTemplateSpringApplication.class);
    application.setAdditionalProfiles("local");
    application.run(args);
  }

}
