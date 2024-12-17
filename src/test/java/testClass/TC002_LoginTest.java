package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test (groups={"sanity", "master"})
	public void verifyLogin()
	{
		logger.info("******TC002_LoginTest Execution started******");
		//Home Page
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		//Login Page
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(properties.getProperty("username"));
		loginPage.setPassword(properties.getProperty("password"));
		loginPage.clickLogin();
		
		//My Account page validation
		MyAccountPage accountPage = new MyAccountPage(driver);
		boolean validation_result = accountPage.targetPage_validation();
		if(validation_result == true)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false, "Test failed");
		accountPage.clickLogout();
		 logger.info("******TC002_LoginTest Execution finished******");
		
	}

}
