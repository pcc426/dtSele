
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DTSinaWebLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String[] SINA_USERNAME = {"18601750455"};
  public String[] SINA_PASSWORD = {"chery1989"};
  private String loginBtnName = "使用微博账号登录";

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.duitang.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testDTSinaWebLogin() throws Exception {
    driver.get(baseUrl);
    boolean b = isElementPresent(By.id("dt-login"));
    driver.findElement(By.id("dt-login")).click();
    if(isElementPresent(By.cssSelector("div#poplogin"))){
    	driver.findElement(By.linkText(loginBtnName)).click();
//    	driver.wait(WAIT_TIME);
    	if(isElementPresent(By.cssSelector("input#userId"))){
    		driver.findElement(By.cssSelector("input#userId")).clear();
        	driver.findElement(By.cssSelector("input#userId")).sendKeys(SINA_USERNAME);
        	driver.findElement(By.cssSelector("input#passwd")).clear();
        	driver.findElement(By.cssSelector("input#passwd")).sendKeys(SINA_PASSWORD);
        	driver.findElement(By.cssSelector("a.WB_btn_login.formbtn_01")).click();
    	}
    	else if(isElementPresent(By.cssSelector("a.WB_btn_login.formbtn_01"))){
    		driver.findElement(By.cssSelector("a.WB_btn_login.formbtn_01")).click();	
    	}
    	
    }
    else{
    	System.out.println("Login Promtwindow Failed!");
    }
    System.out.println(getLoginMsg());
    
  }
  
  private String getLoginMsg() throws Exception{
	  String loginMsg = "Login failed, unknown error!";
	  
	  if(isElementPresent(By.cssSelector("div.prompt.prompt-fail"))){
		  loginMsg = driver.findElement(By.cssSelector("div.prompt.prompt-fail")).getText();
	  }
	  else if(isElementPresent(By.cssSelector("img.dt-avatar"))){
		  loginMsg = loginBtnName+"Login Success!";
	    }
	  else{
		  loginMsg = driver.getTitle();
	  }
	  return loginMsg;
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
