package RHA.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rhas.AbstractComponents.AbstractComponents;

public class Cart extends AbstractComponents {
	
	WebDriver driver;
	
	public Cart(WebDriver driver) {
		super(driver); //send driver to parent (abstract components)
		//initialization of driver
		//first thing to execute
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(css = ".cartSection h3") List<WebElement> cartItems;
	@FindBy(css = ".totalRow button") WebElement checkout;
	
	
	public boolean verifyCartItems(String item) {
		boolean match = cartItems.stream().anyMatch(product -> product.getText().equalsIgnoreCase(item));
		return match;
	}
	
	public Checkout clickCheckout() {
		checkout.click();
		Checkout check = new Checkout(driver);
		return check;
	}
	

}
