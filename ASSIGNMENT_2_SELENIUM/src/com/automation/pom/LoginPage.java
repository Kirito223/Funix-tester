package com.automation.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriverWait wait;
	WebDriver driver;
	Actions actions;

	public LoginPage(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.driver = driver;
		this.actions = new Actions(driver);
	}
	
	public void gotoLogin() throws InterruptedException {
		WebElement loginButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/login']")));
		loginButton.click();
		Thread.sleep(2000);
	}
	
	public void enterEmail(String email) throws InterruptedException {
		WebElement emailInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa=\"login-email\"]")));
		emailInput.sendKeys(email);
		Thread.sleep(2000);
		
	}
	
	public void enterPassword(String password) throws InterruptedException {
		WebElement passwordInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa=\"login-password\"]")));
		passwordInput.sendKeys(password);
		Thread.sleep(2000);
	}

	public void clickSignIn() throws InterruptedException{
		
		WebElement signInButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[data-qa='login-button']"))));
		actions.click(signInButton).build().perform();
		Thread.sleep(2000);
	}
	public void clickLogout() {
		WebElement signout = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));
		signout.click();
	}

	public boolean checkSignOut() {
		boolean result = false;
		try {
			WebElement signout = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));
			if (signout.isDisplayed()) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}
