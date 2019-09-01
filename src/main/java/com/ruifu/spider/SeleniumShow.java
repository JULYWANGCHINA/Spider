package com.ruifu.spider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import util.BrowserUtil;

public class SeleniumShow {

	public static void main(String[] args) {
		
		WebDriver driver = BrowserUtil.getIEDriver();
//		WebDriver driver = BrowserUtil.getFirefoxDriver();
//		WebDriver driver = BrowserUtil.getChromeDriver();
		driver.get("https://www.baidu.com/");
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.open()");
//        System.out.println(driver.getWindowHandles().size());
	}

}
