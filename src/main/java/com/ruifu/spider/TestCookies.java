package com.ruifu.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import util.PropertiesUtil;
/**
 * 
 * @author July.Wang
 *
 */
public class TestCookies {

	public static void main(String[] args) {
		File chromeDriverPath = new File(PropertiesUtil.getProperty(PropertiesUtil.DIRVER_PATH) + "\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriverPath.getAbsolutePath());
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("https://www.iteye.com/");
		writeCookies(webDriver);
		getCookies(webDriver);
	}

	public static void writeCookies(WebDriver webDriver) {

		webDriver.get("https://www.iteye.com/login");
		webDriver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(PropertiesUtil.getProperty("iteye.user"));
		webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PropertiesUtil.getProperty("iteye.pwd"));
		webDriver.findElement(By.xpath("//*[@id=\"auto\"]")).click();

		webDriver.findElement(By.id("button")).click();

		File cookieFile = new File(PropertiesUtil.getProperty(PropertiesUtil.FILE_PATH)+"cookie.txt");

		try {
			cookieFile.delete();
			cookieFile.createNewFile();
			FileWriter fileWriter = new FileWriter(cookieFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Cookie cookie : webDriver.manage().getCookies()) {
				bufferedWriter.write((cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";"
						+ cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure()));
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webDriver.quit();
	}

	public static void getCookies(WebDriver webDriver) {
		BufferedReader bufferedReader;
		webDriver.get("https://www.iteye.com");
		try {
			File cookieFile = new File(PropertiesUtil.getProperty(PropertiesUtil.FILE_PATH)+"cookie.txt");
			FileReader fileReader = new FileReader(cookieFile);
			bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
				while (stringTokenizer.hasMoreTokens()) {

					String name = stringTokenizer.nextToken();
					String value = stringTokenizer.nextToken();
					String domain = stringTokenizer.nextToken();
					String path = stringTokenizer.nextToken();
					Date expiry = null;
					String dt;

					if (!(dt = stringTokenizer.nextToken()).equals("null")) {
						expiry = new Date(dt);
					}

					boolean isSecure = new Boolean(stringTokenizer.nextToken()).booleanValue();
					Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
					webDriver.manage().addCookie(cookie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		webDriver.get("https://www.iteye.com/");

	}
}
