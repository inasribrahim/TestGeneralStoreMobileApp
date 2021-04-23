package generalStore.ecommerceTest;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class ecommerce_tc_2 {
	public  static AndroidDriver<AndroidElement>  driver;

	public  ecommerce_tc_2() throws MalformedURLException 
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
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Converse All Star']")).click();
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Converse All Star\"));");
	   // For fast navigator 
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Converse All Star\").instance(0))"));
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<count;i++){
	    		String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText(); 
	    if(text.equalsIgnoreCase("Converse All Star")){
	    		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
	    		break;
	    }
    }
		// Please add some product at first
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String lastpageText=   driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals("Converse All Star", lastpageText);
}
	// Add Product to cart Shop and verify the name of product & check the toast message error when click cart shop without choose any product 
	@Test
	public void tc_2() throws MalformedURLException{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("android:id/text1")).click();  
	    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Egypt\"));");
	    driver.findElement(By.xpath("//*[@text='Egypt']")).click(); 
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("ibrahim"); // send hello to input text 
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		String actualMessage = "Please add some product at first" ; 
		Assert.assertEquals(actualMessage, toastMessage); 
}
}
