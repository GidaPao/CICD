package RHA.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RHA.SeleniumFrameworkDesign.TestComponents.BaseTest;
import RHA.SeleniumFrameworkDesign.pageobjects.Cart;
import RHA.SeleniumFrameworkDesign.pageobjects.Checkout;
import RHA.SeleniumFrameworkDesign.pageobjects.OrderConfirmation;
import RHA.SeleniumFrameworkDesign.pageobjects.OrderPage;
import RHA.SeleniumFrameworkDesign.pageobjects.ProductCatalog;
import rhas.AbstractComponents.AbstractComponents;

public class StandAloneTestRefactor extends BaseTest {
	//String item = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap <String, String> input) throws IOException{
		// TODO Auto-generated method stub

		
		String country = "Philippines";

		ProductCatalog prodcat = landing.loginApplication(input.get("username"), input.get("password"));
		
		//Product Catalog
		prodcat.addProductToCart(input.get("item"));
		AbstractComponents ac = new AbstractComponents(driver);
		Cart cart = ac.clickCart();
		
		//Cart
		Assert.assertTrue(cart.verifyCartItems(input.get("item")));
		Checkout check = cart.clickCheckout();

		//Checkout
		check.selectCountry(country);
		OrderConfirmation oc = check.clickPlaceOrder();
		
		//Order Confirmation Page
		System.out.println(oc.verifyOrderNumber());
	}
	
	@Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData", groups = {"Purchase"})
	public void OrderHistoryTest(HashMap <String, String> input) {
		ProductCatalog prodcat = landing.loginApplication(input.get("username"), input.get("password"));
		OrderPage op = prodcat.clickOrder();
		Assert.assertTrue(op.VerifyOrderDisplay(input.get("item")));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "1dummy@account.com");
		map.put("password", "T3$tdummy");
		map.put("item", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("username", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("item", "IPHONE 13 PRO");*/
		
		
		List<HashMap<String, String>> data = getJsonToMap(System.getProperty("user.dir") + "\\src\\test\\java\\datapackage\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)},{data.get(1)} };
	}
	/*
	@DataProvider
	public Object[][] getData() {
		return new Object[][] { {"anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO"},{"1dummy@account.com", "T3$tdummy", "ZARA COAT 3" } };
	}
	*/

}
