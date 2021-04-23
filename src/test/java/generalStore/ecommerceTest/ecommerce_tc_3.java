package generalStore.ecommerceTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class ecommerce_tc_3 {
	public  static AndroidDriver<AndroidElement>  driver;

	public  ecommerce_tc_3() throws MalformedURLException 
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
		
	    // Find First  + Second Element on cart Shop
	    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
	    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
	    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	    //TotalPurchaseAmount
	    int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
	    Double  sum = 0.0;
	    for (int i=0 ; i< count ; i++) {
	    	 String amount = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
	    	Double totalAmount = convertStringtoAmount(amount);
	    	 sum += totalAmount;
	    }
	    String amountActual = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	    Double totalActualAmount = convertStringtoAmount(amountActual);
	    Assert.assertEquals(sum, totalActualAmount); 
	 
    }
		
	public static double convertStringtoAmount(String value){
		value= value.substring(1);
		double amount2coin=Double.parseDouble(value);
		return amount2coin;
	}
	// using tap + and perform mobile Gestures
	@Test
	public void tc_2() {
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
		//click on checkBox
		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		//Long press on text 
		WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	}
}
