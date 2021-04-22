package generalStore.ecommerceTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class ecommerce_tc_1 {
	// tc: complete the info of general store and verify toast messages ( describe error for empty input ) 

	public  static AndroidDriver<AndroidElement>  driver;

	public  ecommerce_tc_1() throws MalformedURLException 
	{
	     File appDir = new File("App");
	     File app = new File(appDir, "1.1 General-Store.apk");
	     DesiredCapabilities capabilities = new DesiredCapabilities();
	     
	     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nougat7.1.1");
	     capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ; 
	}

	@Test
	public void tc_1() throws MalformedURLException{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("android:id/text1")).click();  
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Egypt\"));");
	    driver.findElement(By.xpath("//*[@text='Egypt']")).click(); 
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("ibrahim"); // send hello to input text 
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		

	}
	@Test
	public void tc_2() throws MalformedURLException{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys(" "); // send hello to input text 
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		String actualMessage = "Please enter your name" ; 
		Assert.assertEquals(actualMessage, toastMessage); 
	}
	
}
