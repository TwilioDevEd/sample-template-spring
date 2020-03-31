package com.twilio.sampletemplatespring.models;

public class SmsMessage {
  private String recipient;
  private String body;

  public SmsMessage() {}

  public SmsMessage(String recipient, String body) {
    this.recipient = recipient;
    this.body = body;
  }

  /**
   * @return the to
   */
  public String getRecipient() {
    return recipient;
  }

  /**
   * @param to the to to set
   */
  public void setTo(String recipient) {
    this.recipient = recipient;
  }

  /**
   * @return the body
   */
  public String getBody() {
    return body;
  }

  /**
   * @param body the body to set
   */
  public void setBody(String body) {
    this.body = body;
  }
}