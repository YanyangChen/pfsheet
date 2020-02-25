package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestFixedColumn {
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",Base.macdriver);
		WebDriver driver = new ChromeDriver();
	
        String baseUrl = "http://127.0.0.1:8080/websams-handsontable/";
        
        //user get into the page
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        //user tab the column to the last one through tab, mark the fourth column's location
        //Base.Double_click_element("//div[@name='secForm:sheet2_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[2]", driver);
       
        
        WebElement id1 = driver.findElement(By.xpath("//div[@name='secForm:sheet2_tbl']//table/tbody/tr/td[contains(text(), 'chi1')]"));
        
        System.out.println(" id1.getLocation().toString(); : " +  id1.getLocation().toString());
        System.out.println(" id1.getText() : " +  id1.getText());
        //Base.Double_click_element("//div[@name='secForm:sheet2_tbl']//table/tbody/tr/td[contains(text(), '111')]", driver);
        
       

        Actions actions = new Actions(driver);

        actions.moveToElement(id1).click().perform();
        actions.moveToElement(id1).sendKeys(Keys.TAB).perform();
        actions.moveToElement(id1).sendKeys(Keys.ENTER).perform();
        WebElement textarea = driver.findElement(By.className("handsontableInput"));
        System.out.println(" textarea.getLocation1() : " +  textarea.getLocation());
        actions.moveToElement(id1).sendKeys(Keys.TAB).perform();
        actions.moveToElement(id1).sendKeys(Keys.ENTER).perform();
        System.out.println(" textarea.getLocation2() : " +  textarea.getLocation());
        actions.moveToElement(id1).sendKeys(Keys.TAB).perform();
        actions.moveToElement(id1).sendKeys(Keys.ENTER).perform();
        
        System.out.println(" textarea.getLocation3() : " +  textarea.getLocation());
        Point plast2nd = textarea.getLocation();
        actions.moveToElement(id1).sendKeys(Keys.TAB).perform();
        actions.moveToElement(id1).sendKeys(Keys.ENTER).perform();
        System.out.println(" textarea.getLocation4() : " +  textarea.getLocation());
        Point plast1 = textarea.getLocation();
        if (plast1.equals(plast2nd)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        driver.close();
        
        //id1.click();
       
        //when tab to the last column, same location, different value
	}
}
