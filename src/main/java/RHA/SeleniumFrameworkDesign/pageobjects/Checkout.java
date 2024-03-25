package RHA.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rhas.AbstractComponents.AbstractComponents;

public class Checkout extends AbstractComponents {
	
	WebDriver driver;
	
	public Checkout(WebDriver driver) {
		super(driver); //send driver to parent (abstract components)
		//initialization of driver
		//first thing to execute
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(xpath = "//input[@placeholder = 'Select Country']") WebElement countryTextBox;
	@FindBy(css = ".action__submit") WebElement placeOrder;
	
	public void selectCountry(String country) {
		By countryoption = By.xpath("//span[contains(text(), '" + country + "')]");
		countryTextBox.sendKeys(country);
		waitForElementToAppear(countryoption);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(countryoption)).click().build().perform();
	}
	
	public OrderConfirmation clickPlaceOrder() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 500)");
		placeOrder.click();
		OrderConfirmation oc = new OrderConfirmation(driver);
		return oc;
		
	}


}
