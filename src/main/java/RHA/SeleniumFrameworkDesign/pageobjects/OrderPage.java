package RHA.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rhas.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	


	@FindBy (css = "tr td:nth-child(3)") private List<WebElement> productNames;
			
	
	public OrderPage(WebDriver driver) {
		super(driver); //send driver to parent (abstract components)
		//initialization of driver
		//first thing to execute
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public Boolean VerifyOrderDisplay(String item) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(item));
		return match;
		
		
	}
}
