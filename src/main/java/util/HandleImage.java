package util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * 
 * @author July.Wang
 *
 */
public class HandleImage {
	
	public static String handleVerifyCodeImg(WebDriver driver, String verifyCodeId) throws Throwable {
		WebElement verifyCode = driver.findElement(By.id(verifyCodeId));
		return handleVerifyCodeImg(driver, verifyCode, 0, 0, 0, 0);
	}
	
	public static String handleVerifyCodeImg(WebDriver driver, WebElement verifyCode) throws Throwable {
		return handleVerifyCodeImg(driver, verifyCode, 0, 0, 0, 0);
	}
	
	public static String handleVerifyCodeImg(WebDriver driver, String verifyCodeId, int mx, int my, int mwidth,
			int mheight) throws Throwable {
		WebElement verifyCode = driver.findElement(By.id(verifyCodeId));
		return handleVerifyCodeImg(driver, verifyCode, mx, my, mwidth, mheight);
	}
	
	public static String handleVerifyCodeImg(WebDriver driver, WebElement verifyCode, int mx, int my, int mwidth,
			int mheight) throws Throwable {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Dimension size = verifyCode.getSize();

		Integer x = mx != 0 ? mx : verifyCode.getLocation().getX();
		Integer y = my != 0 ? my : verifyCode.getLocation().getY();
		Integer width = mwidth != 0 ? mwidth : size.getWidth();
		Integer height = mheight != 0 ? mheight : size.getHeight();

		File f = new File(PropertiesUtil.getProperty(PropertiesUtil.FILE_PATH) + UUID.randomUUID() + ".png");
		cutImage(new FileInputStream(srcFile), f, x, y, width, height);
		String verifyCodeStr = CaptchaUtil.convert(f, CaptchaUtil.CODETYPE_6); // 调用第三方自动解析验证码接口获取验证码值
		System.out.println(verifyCodeStr);
		f.delete();
		return verifyCodeStr;
	}

	/*
	 * 图片裁剪
	 */
	private static void cutImage(InputStream in, File dest, int x, int y, int w, int h) throws IOException {
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("png");
		ImageReader reader = iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "png", dest);
	}
}
