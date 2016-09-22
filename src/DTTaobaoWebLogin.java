
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

public class DTTaobaoWebLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String[] TAOBAO_USERNAME = {"pcc426"};
  public String[] TAOBAO_PASSWORD = {"ALICE0426@"};
  private String loginBtnName = "使用淘宝账号登录";

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
//	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");   
//	driver = new ChromeDriver();
    baseUrl = "http://www.duitang.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testQqWebLogin() throws Exception {
	  driver.get(baseUrl);
	  driver.findElement(By.id("dt-login")).click();
		  
	    if(isElementPresent(By.cssSelector("div#poplogin"))){
	    	driver.findElement(By.linkText(loginBtnName)).click();
	    	driver.switchTo().frame(0);
//	    	System.out.println(driver.getPageSource());
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//	    	b = isElementPresent(By.cssSelector("a#switcher_plogin"));
//	    	switcher.click();
	    	Actions builder = new Actions(driver);
	    	boolean b = false;
	    
	    	driver.findElement(By.id("TPL_username_1")).clear();
//	    	driver.findElement(By.cssSelector("input#u")).clear();
	    	driver.findElement(By.id("TPL_username_1")).sendKeys(TAOBAO_USERNAME);
	    	driver.findElement(By.id("TPL_password_1")).clear();
	    	driver.findElement(By.id("TPL_password_1")).sendKeys(TAOBAO_PASSWORD);
	    	b = isElementPresent(By.cssSelector("button#J_SubmitStatic"));
//	    	driver.findElement(By.cssSelector("button#J_SubmitStatic")).click();
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].click();",driver.findElement(By.id("J_SubmitStatic")));
	    	driver.switchTo().defaultContent();
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	if(isElementPresent(By.id("sub"))){
	    		driver.findElement(By.id("sub")).click();
	    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	}
	    	else{
	    		System.out.println("Taobao Login Failed!");
	    	}	    	
	    }
	    else{
	    	System.out.println("Login Promtwindow Failed!");
	    }
	    //�ж��Ƿ�����֤��
//	    if(isElementPresent(By.id("ccode"))){
//	      driver.findElement(By.id("ccode")).clear();
//	      driver.findElement(By.id("ccode")).sendKeys(this.getCcode());
//	    }
		  
		  

	    //�ж��Ƿ��ѵ�¼�ɹ�
//	    System.out.print(getLoginMsg());
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
