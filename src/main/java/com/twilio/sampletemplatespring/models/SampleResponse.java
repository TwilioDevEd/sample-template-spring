package com.twilio.sampletemplatespring.models;

/**
 * ResponseBody
 */
public class SampleResponse {

  private String status;
  private String message;

  public SampleResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }
}
