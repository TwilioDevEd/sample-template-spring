package com.twilio.sampletemplatespring;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.exception.ApiException;
import com.twilio.sampletemplatespring.controllers.SampleController;
import com.twilio.sampletemplatespring.models.SmsMessage;
import com.twilio.sampletemplatespring.services.TwilioClientWrapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SampleController.class)
class ExampleTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TwilioClientWrapper twilioClientWrapper;

  @Test
  public void exampleShouldReturnJson() throws Exception {
    this.mockMvc.perform(get("/example"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("{\"example\":true}")));
  }

  @Test
  public void rootShouldContainTitle() throws Exception {
    this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Template App")));
  }

  @Test
  public void sendSmsToInvalidNumber() throws Exception {
    when(twilioClientWrapper.sendMessage(any(SmsMessage.class))).thenThrow(new ApiException("fakeMessageSid"));
    ObjectMapper objectMapper = new ObjectMapper();
    SmsMessage message = new SmsMessage("1111", "Test Message");
    this.mockMvc.perform(post("/send-sms")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(message)))
        .andExpect(status().isInternalServerError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value("error"))
        .andExpect(jsonPath("$.message").value(containsString("Failed to send SMS.")));
  }

  @Test
  public void sendSmsToValidNumber() throws Exception {
    when(twilioClientWrapper.sendMessage(any(SmsMessage.class))).thenReturn("fakeSid");
    ObjectMapper objectMapper = new ObjectMapper();
    SmsMessage message = new SmsMessage("1111", "Test Message");
    this.mockMvc.perform(post("/send-sms")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(message)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status").value("success"))
        .andExpect(jsonPath("$.message").value("SMS sent to 1111. Message SID: fakeSid"));
  }

}
