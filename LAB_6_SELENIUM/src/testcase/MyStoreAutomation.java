package testcase;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyStoreAutomation {
	String url = "";
	String keyword="";
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void init() {
		url = "https://automationexercise.com/products";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@Test
	public void TC_MyOderSearchValid() throws InterruptedException {
		keyword ="Stylish Dress";
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement inputSearch = driver.findElement(By.id("search_product"));
		wait.until(ExpectedConditions.visibilityOf(inputSearch));
		Assert.assertEquals(true, inputSearch.isDisplayed(), "Page load failed");
		
		Actions actions = new Actions(driver);
		actions.sendKeys(inputSearch, keyword).build().perform();// Nhap keyword vo o tim kiem
		actions.click(driver.findElement(By.id("submit_search"))).build().perform();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
//		Kiem tra tim kiem co that bai hay khong
		WebElement resultMessage = driver.findElement(By.className("product-image-wrapper"));
		wait.until(ExpectedConditions.visibilityOf(resultMessage));
		Assert.assertEquals(true, resultMessage.isDisplayed(), "Search Failed");
				
//		 Kiem tra hinh anh co hien thi khong
		WebElement imageLink = driver.findElement(By.cssSelector(".productinfo>img"));
		Assert.assertEquals(true, imageLink.isDisplayed(), "Product load failed");

		//		Kiem tra so luong san pham hien thi
		List<WebElement> productList = driver.findElements(By.className("product-image-wrapper"));
		Assert.assertEquals(productList.size(), 1, "Quanity greater than 1");
		
		WebElement nameProduct = driver.findElement(By.cssSelector(".productinfo>p"));
		String strNameProduct = nameProduct.getText();
		Assert.assertEquals(true, nameProduct.isDisplayed(), "Name product not display");
		Assert.assertEquals(strNameProduct, keyword, "Name are not the same");
		
		WebElement price = driver.findElement(By.cssSelector(".productinfo>h2"));
		Assert.assertEquals(true, price.isDisplayed(), "Price not display");
		
		WebElement addToCart = driver.findElement(By.xpath("//*[@class='features_items']/div[2]/div/div/div/a"));
		Assert.assertEquals(true, addToCart.isDisplayed(), "Button add to cart not display");
		Thread.sleep(500);
		
        actions.sendKeys(addToCart,Keys.PAGE_DOWN).click(addToCart).build().perform();

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement responseMesage = driver.findElement(By.xpath("//div[@id='cartModal']/div/div/div[@class='modal-body']/p[text()='Your product has been added to cart.']"));
		wait.until(ExpectedConditions.visibilityOf(responseMesage));
		Assert.assertEquals(true, responseMesage.isDisplayed(), "Response not display");
	
		String expect ="Your product has been added to cart.";
		String actual = responseMesage.getText();
		Assert.assertEquals(actual, expect, "Response message are not the same");
		
	}
	
	@AfterClass
	public void endTestCase() {
		driver.close();
	}
}
