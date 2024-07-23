package Selenium;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * Run from testng.xml ->> Right click - Run as "testng suite"
 */

public class LoginAPP {
	
	 WebDriver driver;
	    @BeforeTest
	    public void setup() {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
}
	    @Parameters({"username","password"})  		
        @Test
	
       public void loginapp(String username,String password) {
    	driver.navigate().to("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
    	
    	driver.findElement(By.id("input-email")).sendKeys(username);
    	driver.findElement(By.id("input-password")).sendKeys(password);
    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.MILLISECONDS);
    	String url = driver.getCurrentUrl();
	    assertTrue(url.contains("ecommerce-playground.lambdatest.io"));
    	
    	   
    	   
       }
	    @AfterTest
	    public void teardown() {
	        driver.quit();
	    }
	}


