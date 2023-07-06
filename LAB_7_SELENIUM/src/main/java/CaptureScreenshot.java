package main.java;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	public static void takeScreenshot(WebDriver driver, String testcaseName) {
	    try {
	        
	        String imageName = testcaseName+".png";
	        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(screenshot, new File("./ScreenShots/"+ imageName));

	    } catch (Exception ex) {
	        System.out.println("Da xay ra loi khi chup man hinh!");
	        ex.printStackTrace();
	    }
	}
}
