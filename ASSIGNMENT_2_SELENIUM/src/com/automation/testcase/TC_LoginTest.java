package com.automation.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.constants.Config;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {

	@Test(dataProvider = "Excel")
	public void TC01_LoginFirstAccount(String email, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.gotoLogin();
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
		boolean checkLogin = loginPage.checkSignOut();
		if (checkLogin) {
			loginPage.clickLogout();
		} else {
			CaptureScreenshot.takeScreenshot(driver, "Login fail");
		}
	}

	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws Exception {
		FileInputStream inputStream = new FileInputStream(PropertiesFileUtils.getProperty(Config._DATA_DIR));
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalRow = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[totalRow][2];
		int index = 0;
		for (int i = 0; i < totalRow; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell email = (XSSFCell) row.getCell(0);
			XSSFCell username = (XSSFCell) row.getCell(1);
			data[i][0] = email.getStringCellValue();
			data[i][1] = username.getStringCellValue();
		}
		return data;
	}
}
