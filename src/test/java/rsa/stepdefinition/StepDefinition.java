package rsa.stepdefinition;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import RHA.SeleniumFrameworkDesign.TestComponents.BaseTest;
import RHA.SeleniumFrameworkDesign.pageobjects.Cart;
import RHA.SeleniumFrameworkDesign.pageobjects.Checkout;
import RHA.SeleniumFrameworkDesign.pageobjects.LandingPage;
import RHA.SeleniumFrameworkDesign.pageobjects.OrderConfirmation;
import RHA.SeleniumFrameworkDesign.pageobjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rhas.AbstractComponents.AbstractComponents;

public class StepDefinition extends BaseTest{
	
	public LandingPage landing;
	public ProductCatalog prodcat;
	public AbstractComponents ac;
	public Checkout check;
	public OrderConfirmation oc;
	String country = "Philippines";
	
	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landing = launchApplication();
	}
	
	@Given ("^I Logged in with username (.+) and password (.+)$")
	public void I_Logged_in_with_username_and_password(String username, String password) {
		prodcat = landing.loginApplication(username, password);
	}
	
	@When ("^I add the product (.+) to cart$")
	public void I_add_the_product_to_cart(String product) {
		prodcat.addProductToCart(product);
	}
	
	@And ("^Check out (.+)$")
	public void check_out_product(String product) {
		ac = new AbstractComponents(driver);
		Cart cart = ac.clickCart();
		Assert.assertTrue(cart.verifyCartItems(product));
		check = cart.clickCheckout();
	}
	
	@And ("submit the Order")
	public void submit_the_Order() {
		check.selectCountry(country);
		oc = check.clickPlaceOrder();
	}
	
	@And ("Order number is displayed")
	public void Order_number_is_displayed() {
		System.out.println(oc.verifyOrderNumber());
		}
	
	@Then ("{string} is displayed in the confimration page.")
	public void message_displayed_confirmation_message(String string) {
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), string);
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed(String string) {
		Assert.assertEquals(landing.getErrorMessage(), string);
		driver.close();
	}
	

}
	