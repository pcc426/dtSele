

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CommonLoginMod {

	public String[] getCcode() throws Exception {
		  BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		  System.out.println("请输入图中验证码");
		  String[] ccode = {buffer.readLine()};
		  return ccode;
	  }
	
	
}
