package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 
 * @author July.Wang
 *
 */
public class BrowserUtil {

	public static WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				PropertiesUtil.getProperty(PropertiesUtil.DIRVER_PATH) + "chromedriver.exe");
		// 实例化一个Chrome浏览器的实例
		WebDriver driver = new ChromeDriver();
		// driver.manage() 这个接口下的操作都是用来控制浏览器本身的
		driver.manage().window().maximize();

		// Dimension targetSize = new Dimension(1024, 768);
		// driver.manage().window().setSize(targetSize);
		// Point targetPosition = new Point(500, 200); //设置窗口左上角那个点出现的位置为（x, y）
		// driver.manage().window().setPosition(targetPosition);

		// 设置隐性的等待时间
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver getFirefoxDriver() {

		// 在selenium下载页面 http://www.seleniumhq.org/download/ （需FQ）
		// 各个版本的changelog中，会记录支持的firefox版本

		System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		// 加载驱动
		System.setProperty("webdriver.gecko.driver",
				PropertiesUtil.getProperty(PropertiesUtil.DIRVER_PATH) + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		// 窗口最大化
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver getIEDriver() {
		System.setProperty("webdriver.ie.driver",
				PropertiesUtil.getProperty(PropertiesUtil.DIRVER_PATH) + "IEDriverServer.exe");
		// 代码关闭IE一些配置
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		dc.setCapability("ignoreProtectedModeSettings", true);
		// 初始化IE浏览器实例
		WebDriver driver = new InternetExplorerDriver();
		// 最大化窗口
		driver.manage().window().maximize();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}
}
