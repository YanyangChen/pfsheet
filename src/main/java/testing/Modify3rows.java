package testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class Modify3rows {
	WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws Exception {
       
    	System.setProperty("webdriver.chrome.driver",Base.macdriver);
		WebDriver driver = new ChromeDriver();
	
        String baseUrl = "http://127.0.0.1:8080/websams-handsontable/";
       

        driver.get(baseUrl);

        //WebElement id1 = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]"));


        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]", driver);
        clear_and_send_key("111", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[3]", driver);
        clear_and_send_key("112", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[4]", driver);
        clear_and_send_key("113", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[5]", driver);
        clear_and_send_key("114", driver);
        
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[2]", driver);
        clear_and_send_key("115", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[3]", driver);
        clear_and_send_key("116", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[4]", driver);
        clear_and_send_key("117", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[5]", driver);
        clear_and_send_key("118", driver);
        
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[2]", driver);
        clear_and_send_key("119", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[3]", driver);
        clear_and_send_key("120", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[4]", driver);
        clear_and_send_key("121", driver);
        Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[5]", driver);
        clear_and_send_key("122", driver);
        
        WebElement save_button = driver.findElement(By.name("iconOnly"));
        save_button.click();
        

        driver.get(baseUrl);
        
        WebElement test = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]"));
        if (test.getText().equals("111")){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        driver.close();
       
    }
    
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
     id1.click();
     wait.until(ExpectedConditions.elementToBeClickable(id1));
     id1.click();
    	 
    	}
    
    

}