package RHA.SeleniumFrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import RHA.SeleniumFrameworkDesign.TestComponents.BaseTest;
import RHA.SeleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test (groups = {"ErrorHandling"}, retryAnalyzer = Retry.class )
	public void LoginErrorValidation() throws IOException{
		// TODO Auto-generated method stub


		landing.loginApplication("1dummy@account.com", "T3$tdummyerror");
		Assert.assertEquals("Incorrect email or password.", landing.getErrorMessage());
		
	}

}
