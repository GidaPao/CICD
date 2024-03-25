package RHA.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rhas.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		//initialization of driver
		//first thing to execute
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css = ".card-body") List<WebElement> products; //List<WebElement> products = driver.findElements(By.cssSelector(".card-body"))
	@FindBy (css = ".ng-animating") WebElement spinner;
	
	
	
	By productsBy = By.cssSelector(".card-body");
	By addToCart = By.cssSelector(".w-10");
	By toastContainer = By.id("toast-container");
	
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	
	public WebElement getProductByName(String item) {
		WebElement prod = getProductList().stream()
		.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst()
		.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String item) {
		WebElement prod = getProductByName(item);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(spinner);
	}
	

	
}
