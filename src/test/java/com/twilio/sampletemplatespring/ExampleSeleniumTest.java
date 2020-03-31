package com.twilio.sampletemplatespring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.twilio.exception.ApiException;
import com.twilio.sampletemplatespring.models.SmsMessage;
import com.twilio.sampletemplatespring.services.TwilioClientWrapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * ExampleSeleniumTest
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleSeleniumTest {

  @LocalServerPort
  private int port;

  private WebDriver driver;

  @MockBean
  private TwilioClientWrapper twilioClientWrapper;

  @BeforeAll
  public static void setupClass() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void setupTest() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    driver = new ChromeDriver(options);
  }

  @AfterEach
  public void teardown() {
    if (driver != null) {
        driver.quit();
    }
  }

  @Test
  public void sendMessageInvalidNumber() {
    when(twilioClientWrapper.sendMessage(any(SmsMessage.class))).thenThrow(new ApiException("fakeMessageSid"));
    driver.get("http://localhost:" + port + "/");
    WebElement phoneNumber = driver.findElement(By.name("to"));
    phoneNumber.sendKeys("111");
    WebElement body = driver.findElement(By.name("body"));
    body.sendKeys("Test Message");
    WebElement submit = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
    submit.click();
    WebElement dialog = new WebDriverWait(driver, 3)
        .until(driver -> driver.findElement(By.className("alert-danger")));
    assertThat(dialog.getAttribute("class"), not(containsString("d-none")));
    WebElement dialogTitle = driver.findElement(By.id("dialogTitle"));
    WebElement dialogContent = driver.findElement(By.id("dialogContent"));
    assertThat(dialogTitle.getText(), containsString("Error"));
    assertThat(dialogContent.getText(), containsString("Failed to send SMS."));
  }

  @Test
  public void sendMessageValidNumber() {
    when(twilioClientWrapper.sendMessage(any(SmsMessage.class))).thenReturn("fakeSid");
    driver.get("http://localhost:" + port + "/");
    WebElement phoneNumber = driver.findElement(By.name("to"));
    phoneNumber.sendKeys("111");
    WebElement body = driver.findElement(By.name("body"));
    body.sendKeys("Test Message");
    WebElement submit = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
    submit.click();
    WebElement dialog = new WebDriverWait(driver, 3)
        .until(driver -> driver.findElement(By.className("alert-success")));
    assertThat(dialog.getAttribute("class"), not(containsString("d-none")));
    WebElement dialogTitle = driver.findElement(By.id("dialogTitle"));
    WebElement dialogContent = driver.findElement(By.id("dialogContent"));
    assertThat(dialogTitle.getText(), containsString("SMS Sent!"));
    assertThat(dialogContent.getText(), containsString("SMS sent to 111."));
  }

}
