package generalStore.ecommerceTest;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.crypto.KeyAgreementSpi;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

public class ecommerce_tc_4 {
	public  static AndroidDriver<AndroidElement>  driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	public  ecommerce_tc_4() throws MalformedURLException 
	{
	     File appDir = new File("App");
	     File app = new File(appDir, "1.1 General-Store.apk");
	     
	     
	     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nougat7.1.1");
	     capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ; 
	}

	@Test
	public void testHybridApp() throws MalformedURLException, InterruptedException{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("android:id/text1")).click();  
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Egypt\"));");
	    driver.findElement(By.xpath("//*[@text='Egypt']")).click(); 
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("ibrahim"); // send hello to input text 
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	   // Find First  + Second Element on cart Shop
	    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
	    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	    TouchAction t=new TouchAction(driver);
		WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(7000);
		Set<String> contexts = driver.getContextHandles();
		for(String contextName:contexts ) {
			System.out.println(contextName);
		}
		 /*
		  * NATIVE_APP
			WEBVIEW_com.androidsample.generalstore
		  * */
	//capabilities.setCapability("browserName", "Chrome");	
		// Solve chromeDriver 
		// nogat7.1  = webview = 52.0.2743.100 
		// Downlod chromeDriver support verison 52.0.2743.100
		//----------ChromeDriver v2.23 (2016-08-04)----------
		// Supports Chrome v51-53
	driver.context("WEBVIEW_com.androidsample.generalstore");
	Thread.sleep(7000);
	driver.findElement(By.name("q")).sendKeys("hybrid app appium");
	driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	driver.pressKey(new KeyEvent(AndroidKey.BACK));
	driver.context("NATIVE_APP");


    }
		
}
