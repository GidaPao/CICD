package RHA.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RHA.SeleniumFrameworkDesign.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landing;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fls = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Globaldata.properties");
		prop.load(fls);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			// options.setBinary("C:\\Users\\Lenovo\\Downloads\\chromedriver-win64\\chromedriver-win64");
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Lenovo\\Downloads\\chromedriver-win64 new\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge
		} else if (browserName.equalsIgnoreCase("safari")) {
			// safari
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {

		// Read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap - Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File image = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(image, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true) // alwaysRun runs the code even for groups
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landing = new LandingPage(driver);
		landing.goTo();
		return landing;
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.quit();
	}
}
