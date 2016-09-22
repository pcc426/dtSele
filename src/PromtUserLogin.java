
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PromtUserLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String[] USERNAME = {"rrrttt"}; //用户名最好是英文，中文暂时无法识别
  public String[] PASSWORD = {"duitang2014"};

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.duitang.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPromtUserLogin() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("dt-login")).click();
    driver.findElement(By.id("p-username")).clear();
    driver.findElement(By.id("p-username")).sendKeys(USERNAME);
    driver.findElement(By.id("p-password")).clear();
    driver.findElement(By.id("p-password")).sendKeys(PASSWORD);

//    CommonLoginMod clm = new CommonLoginMod();    
//    if(isElementPresent(By.cssSelector("input.ccode"))){
//    	WebElement ccode = driver.findElement(By.cssSelector("input.ccode"));
//        ccode.clear();
//        ccode.sendKeys(clm.getCcode());
//      }
    driver.findElement(By.id("poplogin-rem")).click();
    driver.findElement(By.cssSelector("button.pg-loginbtn")).click();
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
