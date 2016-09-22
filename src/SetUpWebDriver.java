
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

public class SetUpWebDriver {

	private WebDriver driver;
	private String baseUrl = "http://www.duitang.com";
	
	//setup webdriver
	public void setUpDriver(String driverName) throws Exception {
		switch(driverName){
		case "FireFox":
			driver = new FirefoxDriver();
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");   
			driver = new ChromeDriver();
		case "IE":
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Internet Explorer\\iexplore.exe");   
			driver = new InternetExplorerDriver();
		}
//	    driver = new FirefoxDriver();
//		  System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");   
//		  driver = new ChromeDriver();
		  
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
	

}
