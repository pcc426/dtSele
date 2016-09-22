
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DTQQWebLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String[] QQ_USERNAME = {"2451024395"};
  public String[] QQ_PASSWORD = {"mpptvcom2012"};
  private String loginBtnName = "使用QQ账号登录";

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
//	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");    
//    driver = new ChromeDriver();
    baseUrl = "http://www.duitang.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testQqWebLogin() throws Exception {
	  driver.get(baseUrl);
	   driver.findElement(By.id("dt-login")).click();
		  
	    if(isElementPresent(By.cssSelector("div#poplogin"))){
	    	driver.findElement(By.linkText(loginBtnName)).click();
	    	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#ptlogin_iframe")));
	    	
	    	WebElement plogin = driver.findElement(By.id("switcher_plogin"));
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].click();",plogin);
	    	
	    	driver.findElement(By.cssSelector("input#u")).clear();
	    	driver.findElement(By.cssSelector("input#u")).sendKeys(QQ_USERNAME);
	    	driver.findElement(By.cssSelector("input#p")).clear();
	    	driver.findElement(By.cssSelector("input#p")).sendKeys(QQ_PASSWORD);

	    	js.executeScript("arguments[0].click();",driver.findElement(By.id("login_button")));
	    	driver.switchTo().defaultContent();

	    }
	    else{
	    	System.out.println("Login Promtwindow Failed!");
	    }
	   
		  

	    //�ж��Ƿ��ѵ�¼�ɹ�
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
