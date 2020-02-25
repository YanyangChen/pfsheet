package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	public static String macdriver="/Users/Shared/Work/EDB/chromedriver";
	public static String edbpcdriver="D:\\test\\chromedriver_win32_79\\chromedriver.exe";
    public static void clear_and_send_key(String text, WebDriver driver) {
    	
    	WebElement textarea = driver.findElement(By.className("handsontableInput"));
        
        textarea.clear();
        textarea.sendKeys(text);
        textarea.sendKeys(Keys.ENTER);
    	 
    	}
    
	public static void Double_click_element(String text, WebDriver driver) {
	    	
		 WebElement id1 = driver.findElement(By.xpath(text));
	
	     
	     WebDriverWait wait = new WebDriverWait(driver, 10);
	     
	     wait.until(ExpectedConditions.elementToBeClickable(id1));
	     wait.until(ExpectedConditions.visibilityOfAllElements(id1));
	     
	     id1.click();
	     
	     wait.until(ExpectedConditions.elementToBeClickable(id1));
	     id1.click();
	    	 
	    	}
}
