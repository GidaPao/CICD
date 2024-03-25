package rhas.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RHA.SeleniumFrameworkDesign.pageobjects.Cart;
import RHA.SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	@FindBy (css = "[routerlink*='cart']") WebElement cart;
	@FindBy (css = "[routerlink*='myorders']") WebElement orders;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	public Cart clickCart() {
		cart.click();
		Cart cart = new Cart(driver);
		return cart;
	}
	
	public OrderPage clickOrder() {
		orders.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}

}
