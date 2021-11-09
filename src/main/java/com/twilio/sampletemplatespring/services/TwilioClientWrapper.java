package com.twilio.sampletemplatespring.services;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.sampletemplatespring.models.SmsMessage;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TwilioClientWrapper
 */
@Service
public class TwilioClientWrapper {

  private String authToken;
  private String phoneNumber;
  private String accountSid;

  public TwilioClientWrapper(
      @Value("${environment.TWILIO_AUTH_TOKEN}") String authToken,
      @Value("${environment.TWILIO_ACCOUNT_SID}") String accountSid,
      @Value("${environment.TWILIO_PHONE_NUMBER}") String phoneNumber) {
    this.authToken = authToken;
    this.accountSid = accountSid;
    this.phoneNumber = phoneNumber;
  }

  public String sendMessage(SmsMessage smsMessage) throws ApiException {
    Twilio.init(accountSid, authToken);
    Message message = Message
        .creator(new PhoneNumber(smsMessage.getRecipient()), // to
            new PhoneNumber(phoneNumber), // from
            smsMessage.getBody())
        .create();
    return message.getSid();
  }
}
