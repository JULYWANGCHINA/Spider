package com.ruifu.spider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import util.BrowserUtil;
import util.HttpClientUtil;
import util.PropertiesUtil;

public class TestHttpClient {

	public static final String UTF8 = "UTF-8";
	

	public static void main(String[] args) throws Throwable {
		driverAndHttpClient();
	}
	
	public static void driverAndHttpClient() throws Exception{
		String url = "https://channel.duobaocrab.074pr8.cn/#/login";
		WebDriver driver = BrowserUtil.getChromeDriver();
//		WebDriver driver = new HtmlUnitDriver();
//		WebDriver driver = BrowserUtil.getPhantomjs();
		driver.get(url);
		System.out.println(driver.getPageSource());
		driver.findElement(By.id("name")).sendKeys(PropertiesUtil.getProperty("doubao.user"));
		driver.findElement(By.id("password")).sendKeys(PropertiesUtil.getProperty("doubao.pwd"));
		driver.findElement(By.xpath("//*[@id='root']/div/div/div/form/button")).click();

		String type = "";
		String refreshToken = "";
		String cookieStr = "";
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("type"))
				type = cookie.getValue();
			if (cookie.getName().equals("accessToken"))
				refreshToken = cookie.getValue();
			cookieStr = cookie.getName()+"="+cookie.getValue()+"; ";
		}
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", type + " " + refreshToken);
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("Referer", "https://channel.duobaocrab.074pr8.cn/");
		header.put("Sec-Fetch-Mode", "cors");
		header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
		header.put("Cookie", cookieStr);
		
		url = "https://channel.duobaocrab.074pr8.cn/v2/admin/channel/customer/list?range-picker=&channelId=125";
		String result = HttpClientUtil.get(url, header, null);
		System.out.println(result);
	}
	
}
