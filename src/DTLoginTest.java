
import java.io.*;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class DTLoginTest {

  public String[] USERNAME = {"rrrttt"}; //用户名尽量用英文比较好
  public String[] PASSWORD = {"duitang2014"};
  public String[] QQ_USERNAME = {"2451024395"};
  public String[] QQ_PASSWORD = {"mpptvcom2012"};
  private int ALERTSECONDS = 10;
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
//	  System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");   
//	  driver = new ChromeDriver();
	  
    baseUrl = "http://www.duitang.com";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
    
  public void DTLoginPageTest() throws Exception {
    driver.get(baseUrl + "/login/");
    driver.findElement(By.name("login_name")).clear();
    driver.findElement(By.name("login_name")).sendKeys(USERNAME);
    driver.findElement(By.name("pswd")).clear();
    driver.findElement(By.name("pswd")).sendKeys(PASSWORD);
    //判断是否有验证码
//    Boolean isCcodePresent = isElementPresent(By.id("ccode"));
//    if(isCcodePresent){
    if(isElementPresent(By.id("ccode"))){
      driver.findElement(By.id("ccode")).clear();
      driver.findElement(By.id("ccode")).sendKeys(this.getCcode());
    }
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    //判断是否已登录成功
    System.out.print(getLoginMsg());
    
  }
  
  public void DTLoginPromtTest() throws Exception {
	    driver.navigate().to(baseUrl);
//	  	driver.get(baseUrl);
//	    driver.wait(5000);
	    boolean b = isElementPresent(By.id("dt-login"));
	    driver.findElement(By.id("dt-login")).click();
//	    b = isElementPresent(By.cssSelector("div#poplogin"));
	    if(isElementPresent(By.cssSelector("div#poplogin"))){
	    	driver.findElement(By.linkText("使用QQ账号登录")).click();
//	    	driver.wait(3000);
	    	b = isElementPresent(By.cssSelector("iframe#ptlogin_iframe"));
//	    	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#ptlogin_iframe")));
	    	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#ptlogin_iframe")));
	    	b = isElementPresent(By.cssSelector("a#switcher_plogin"));
	    	driver.findElement(By.cssSelector("a#switcher_plogin")).click();
	    	driver.findElement(By.cssSelector("input#u")).clear();
	    	driver.findElement(By.cssSelector("input#u")).sendKeys(QQ_USERNAME);
	    	driver.findElement(By.cssSelector("input#p")).clear();
	    	driver.findElement(By.cssSelector("input#p")).sendKeys(QQ_PASSWORD);
	    	driver.findElement(By.cssSelector("input#login-button")).click();
	    	driver.switchTo().defaultContent();
	    	driver.wait(3000);
	    	
	    }
	    else{
	    	System.out.println("没有弹出登录框！");
	    }
	    //判断是否有验证码
//	    if(isElementPresent(By.id("ccode"))){
//	      driver.findElement(By.id("ccode")).clear();
//	      driver.findElement(By.id("ccode")).sendKeys(this.getCcode());
//	    }

	    //判断是否已登录成功
	    System.out.print(getLoginMsg());
	    
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
      driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent(int seconds){
	  long start = System.currentTimeMillis();
	  long time = System.currentTimeMillis() - start;
	  while(time < seconds * 1000){
		  try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
	  }
	  return false;    
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
  
  private String[] getCcode() throws Exception {
	  BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	  System.out.println("请图中输入验证码");
	  String[] ccode = {buffer.readLine()};
	  return ccode;
  }
  
  private String getLoginMsg() throws Exception{
	  String loginMsg = "未知错误！";
	  
	  if(isElementPresent(By.cssSelector("div.prompt.prompt-fail"))){
		  loginMsg = driver.findElement(By.cssSelector("div.prompt.prompt-fail")).getText();
	  }
	  else if(isElementPresent(By.cssSelector("img.dt-avatar"))){
//	    	System.out.print("登录成功！");
		  loginMsg = "登录成功！";
	    }
	  else{
		  loginMsg = driver.getTitle();
	  }
	  return loginMsg;
  }
  
  
  
}
