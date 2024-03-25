package RHA.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RHA.SeleniumFrameworkDesign.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String item = "IPHONE 13 PRO";
		String country = "Philippines";
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("1dummy@account.com");
		driver.findElement(By.id("userPassword")).sendKeys("T3$tdummy");
		driver.findElement(By.id("login")).click();
		
		
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		
		/* old Way
		for(int i = 0; i<products.size(); i++) {
			String itemname = driver.findElements(By.xpath("//div[@class = 'card-body']/h5/b")).get(i).getText().trim();
			if (itemname.equals(item)) {
				driver.findElements(By.cssSelector(".w-10")).get(i).click();
				break;
				}
		}*/
		
		WebElement prod = products.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".w-10")).click();
		
		//driver.findElement(By.id("toast-container")).isDisplayed();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		/*
		Assert.assertEquals(driver.findElement(By.cssSelector(".cartSection h3")).getText(),item);
		*/
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartItems.stream().anyMatch(product->product.getText().equalsIgnoreCase(item));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//ng-animating
		driver.findElement(By.xpath("//input[@placeholder = 'Select Country']")).sendKeys(country);
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[contains(text(), '"+country+"')]"))).click().build().perform();
		
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String ordernumber = driver.findElement(By.xpath("//label[@class = 'ng-star-inserted']")).getText().split(" ")[1].split(" ")[0];
		System.out.println(ordernumber);
		
		
	}

}
