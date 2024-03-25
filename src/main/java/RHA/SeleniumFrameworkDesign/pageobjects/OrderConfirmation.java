package RHA.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rhas.AbstractComponents.AbstractComponents;

public class OrderConfirmation extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderConfirmation(WebDriver driver) {
		super(driver); //send driver to parent (abstract components)
		//initialization of driver
		//first thing to execute
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(xpath = "//label[@class = 'ng-star-inserted']") WebElement orderNumber;
	
	public String verifyOrderNumber() {
		String ordernumber = orderNumber.getText().split(" ")[1].split(" ")[0];
		return ordernumber;
	}
	

}
