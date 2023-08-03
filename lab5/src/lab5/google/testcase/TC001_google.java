package lab5.google.testcase;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	String email = "duc@test.com";

	@BeforeMethod
	public void init() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}

	@Test
	public void TC001_GoogleSeach() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://courses.funix.edu.vn/login");
		WebElement loginInput = driver.findElement(By.id("login-email"));
		WebElement loginButton = driver.findElement(By.className("login-button"));
		loginInput.sendKeys(email);		
		loginButton.click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("ul.message-copy>li"))));
		WebElement message = driver.findElement(By.cssSelector("ul.message-copy>li"));
		String text = message.getText();
		System.out.println("Ket qua: "+text);
	
	}

	@AfterMethod
	public void terminate() {
		driver.close();
	}

}
