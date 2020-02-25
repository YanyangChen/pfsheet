package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestNestedHeader {
	
	public static void main(String[] args) throws Exception {
		
		
		//initialization
	 	System.setProperty("webdriver.chrome.driver",Base.macdriver);
		WebDriver driver = new ChromeDriver();
        String baseUrl = "http://127.0.0.1:8080/websams-handsontable/";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //user go into the nested header page
        driver.get(baseUrl);
        
        //test nested
        
        //user find that the first row of header has colspan.
        WebElement nested_header = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'main course')]]/td[1]"));
        
        System.out.println("nested_header.getAttribute(\"colspan\") : " +  nested_header.getAttribute("colspan"));
        Boolean nested_header_test = Integer.parseInt(nested_header.getAttribute("colspan")) > 1;
        if (nested_header_test){
            System.out.println("Nested Test Passed!");
        } else {
            System.out.println("Nested Test Failed");
        }
        
        //test header
        //user double click the header and try to set new values to it but it is not allowed.
        WebElement header1_b4 = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'main course')]]/td[1]"));
        System.out.println("header1_b4 : " +  header1_b4.getText());
        Base.Double_click_element("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'main course')]]/td[1]", driver);
//        WebElement textarea = driver.findElement(By.className("handsontableInput"));
        Base.clear_and_send_key("test_header", driver);
        WebElement header1 = driver.findElement(By.xpath("//div[@name='mainForm:sheet_tbl']//table/tbody/tr[./td[contains(text(), 'main course')]]/td[1]"));
        System.out.println("header1 : " +  header1.getText());
        Boolean header_test = header1_b4.getText().equals(header1.getText());
        if (header_test){
            System.out.println("Header Integrity Test Passed!");
        } else {
            System.out.println("Header Integrity Test Failed");
        }
        
        driver.close();
		
	}

}
