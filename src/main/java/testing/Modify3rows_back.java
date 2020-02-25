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
public class Modify3rows_back {
	WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws Exception {
       
    	System.setProperty("webdriver.chrome.driver",Base.macdriver);
		WebDriver driver = new ChromeDriver();
	
        String baseUrl = "http://127.0.0.1:8080/websams-handsontable/";
       

        driver.get(baseUrl);

        //WebElement id1 = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]"));


        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]", driver);
        Base.clear_and_send_key("1", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[3]", driver);
        Base.clear_and_send_key("2", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[4]", driver);
        Base.clear_and_send_key("3", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[5]", driver);
        Base.clear_and_send_key("4", driver);
        
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[2]", driver);
        Base.clear_and_send_key("5", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[3]", driver);
        Base.clear_and_send_key("6", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[4]", driver);
        Base.clear_and_send_key("7", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi2')]]/td[5]", driver);
        Base.clear_and_send_key("8", driver);
        
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[2]", driver);
        Base.clear_and_send_key("9", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[3]", driver);
        Base.clear_and_send_key("10", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[4]", driver);
        Base.clear_and_send_key("11", driver);
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[5]", driver);
        Base.clear_and_send_key("12", driver);
        
        WebElement save_button = driver.findElement(By.name("iconOnly"));
        save_button.click();
        
        driver.get(baseUrl);
        
        WebElement test = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]"));
        if (test.getText().equals("1")){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        driver.close();
       
    }
    

    
    

}