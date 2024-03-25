package RHA.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rhas.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver); //send driver to parent (abstract components)
		//initialization of driver
		//first thing to execute
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id = "userEmail") WebElement userEmail;
	@FindBy(id = "userPassword") WebElement passwordField;
	@FindBy(id = "login") WebElement login;
	@FindBy(id = "toast-container") WebElement errorPopUp;
	
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordField.sendKeys(password);
		login.click();
		ProductCatalog prodcat = new ProductCatalog(driver);
		return prodcat;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		//waitForWebElementToAppear(errorPopUp);
		String errorMessage = errorPopUp.getText();
		return errorMessage;
	}
}
