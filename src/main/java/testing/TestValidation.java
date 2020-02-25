package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestValidation {
	
	 public static void main(String[] args) throws Exception {
		 
		 	//initialization
		 	System.setProperty("webdriver.chrome.driver",Base.macdriver);
			WebDriver driver = new ChromeDriver();
	        String baseUrl = "http://127.0.0.1:8080/websams-handsontable/";
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        //user go into the nested header page
	        driver.get(baseUrl);
	        
	        //user inputed wrong text in a cells
	        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[4]", driver);
	        Base.clear_and_send_key("000", driver);
	        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[5]", driver);
	        Base.clear_and_send_key("111", driver);
	        
	        
	        //user click to the invalid cell 1
	       
	        WebElement test = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[4]"));
	        wait.until(ExpectedConditions.elementToBeClickable(test));
	        test.click();

	        //System.out.println("test.getAttribute(\"style\").toString() : "+test.getAttribute("style").toString());
	        
	        if (test.getAttribute("style").toString().contains("red")){
	            System.out.println("Cell 1 validation color Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        }
	        
	        //user gets error area style from cell comments 1
	        
	        WebElement comment = driver.findElement(By.className("htComments"));
	        wait.until(ExpectedConditions.elementToBeClickable(comment));
	        comment.click();

	       // System.out.println("comment.getAttribute(\"style\") : "+comment.getAttribute("style").toString());
	        
	        if (comment.getAttribute("style").toString().contains("display: block")){
	            System.out.println("Cell 1 validation area style Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        }
	        
	        //user clicks to other place to disapper the current comment area
	        WebElement other = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi3')]]/td[2]"));
	        wait.until(ExpectedConditions.elementToBeClickable(other));
	        other.click();
	        
	        
	        //user click to the invalid cell 2
		       
	        WebElement test2 = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'chi1')]]/td[5]"));
	        wait.until(ExpectedConditions.elementToBeClickable(test));
	        test2.click();

	        //System.out.println("test.getAttribute(\"style\").toString() : "+test2.getAttribute("style").toString());
	        
	        if (test2.getAttribute("style").toString().contains("yellow")){
	            System.out.println("Cell 2 validation color Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        }
	        
	        //user gets error area style from cell comments 2
	        
	        WebElement comment2 = driver.findElement(By.className("htComments"));
	        wait.until(ExpectedConditions.elementToBeClickable(comment2));
	        comment2.click();

	        //System.out.println("comment.getAttribute(\"style\") : "+comment2.getAttribute("style").toString());
	        if (comment2.getAttribute("style").toString().contains("display: block")){
	            System.out.println("Cell 2 validation area style Test Passed!");
	        } else {
	            System.out.println("Test Failed");
	        }
	        
	        driver.close();
	 }

}
