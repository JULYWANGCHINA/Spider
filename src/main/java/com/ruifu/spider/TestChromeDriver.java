package com.ruifu.spider;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import util.HandleImage;
import util.PropertiesUtil;

/**
 * Hello world!
 *
 */
public class TestChromeDriver 
{
    public static void main( String[] args ) {
    	jiansheLogin();
    }
    
    
    public static void jiansheLogin(){
    	try{
    		String user = PropertiesUtil.getProperty("jianshe.user");
    		String pwd = PropertiesUtil.getProperty("jianshe.pwd");
    		String phone = PropertiesUtil.getProperty("jianshe.phone");
    		
    		ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", PropertiesUtil.getProperty(PropertiesUtil.DIRVER_PATH)+"chromedriver.exe");
            options.addArguments("--test-type");
            WebDriver driver = new ChromeDriver(options);
            driver.get("http://creditcard.ccb.com/cn/creditcard/jf_query_login.html");
            driver.manage().window().maximize();
            Thread.sleep(100);
            driver =  driver.switchTo().frame(driver.findElement(By.id("jfquery")));
            
            driver = driver.switchTo().frame(driver.findElement(By.id("itemiframe")));
            //driver.switchTo().defaultContent()
            //driver.switchTo().parentFrame()
            
            driver.findElement(By.id("ACC_NO_temp")).sendKeys(user);
            driver.findElement(By.id("PHONE")).sendKeys(phone);
            WebElement logPass = driver.findElement(By.id("LOGPASS"));
            logPass.click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id='keybordLOGPASS']/h2/span[2]")).click();
            logPass.sendKeys(pwd);

            String verifyCode = HandleImage.handleVerifyCodeImg(driver, "fujiama", 0 , 170+260, 0, 0);
            driver.findElement(By.id("PT_CONFIRM_PWD")).sendKeys(verifyCode);
            
            driver.findElement(By.className("yzma")).click();
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入短信验证码：");
            String yzma = scan.nextLine();
            scan.close();
            
            driver.switchTo().alert().accept();
            
            driver.findElement(By.id("CONFIRMPWD")).sendKeys(yzma);
            
            driver.findElement(By.className("btn_blue")).click();
            
    	}catch(Throwable e){
    		e.printStackTrace();
    	}
    }
    
   
}
