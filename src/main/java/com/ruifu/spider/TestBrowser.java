package com.ruifu.spider;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import util.BrowserUtil;

/**
 * 
 * @author July.Wang
 *
 */
public class TestBrowser {

	public static void main(String[] args) {

		WebDriver ieDriver = BrowserUtil.getIEDriver();
		ieDriver.get("https://www.baidu.com/");
		WebDriver firefoxDriver = BrowserUtil.getFirefoxDriver();
		firefoxDriver.get("https://www.baidu.com/");
		WebDriver chromeDriver = BrowserUtil.getChromeDriver();
		chromeDriver.get("https://www.baidu.com/");

		JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
		js.executeScript("window.open('https://www.csdn.net/')");
		System.out.println(chromeDriver.getWindowHandles().size());
	}

}
