package com.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.automation.constants.Config;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	protected WebDriver driver;

	@BeforeClass
	public void initDriverInstance() {
		String webDriver = PropertiesFileUtils.getProperty(Config._DRIVER);
		WebDriverManager.chromedriver().setup();
		if(webDriver.equalsIgnoreCase("chorme")) {
			driver = new ChromeDriver();			
		}else if (webDriver.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();	
		}else if (webDriver.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();	
		}
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void openWebsite() {
		driver.get(PropertiesFileUtils.getProperty(Config._BASEURL));
		
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if(result.FAILURE == result.getStatus()) {
			try {
				String email = (String) result.getParameters()[0];
				int index = email.indexOf("@");
				String accountName = email.substring(0, index);
				CaptureScreenshot.takeScreenshot(driver, accountName);
				System.out.println("Da chup anh loi "+ result.getTestName());
			} catch (Exception e) {
				System.out.println("error take screen"+ e.getMessage());
			}
		}
	}

	@AfterClass
	public void closeDriverInstance() {
		driver.quit();
	}

}
