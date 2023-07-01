package lab5.google.testcase;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001_google {

	public ChromeDriver driver;

	@BeforeMethod
	public void init() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}

	@Test
	public void TC001_GoogleSeach() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://www.google.com.vn/?hl=vi");
		WebElement inputSearch = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("APjFqb"))));
		inputSearch.sendKeys("selenium");
		WebElement btnSearch = driver.findElement(By.name("btnK"));
		btnSearch.click();
		

	}

	@AfterMethod
	public void terminate() {
		driver.close();
	}

}