package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {


	protected WebDriver driver;
	protected static WebDriverWait wait1; 
	
	public AbstractPage (WebDriver driver) {
		this.driver = driver;
		wait1 = new  WebDriverWait(driver, 15);
		PageFactory.initElements(driver, this);
	}
	
	

	public WebDriver getDriver() {
		return this.driver;
	}
	
	

}
