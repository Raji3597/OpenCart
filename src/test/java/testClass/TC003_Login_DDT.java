package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_Login_DDT extends  BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="master") // If data providers is from another class
	public void loginVerifyDDT(String email, String password, String expected)
	{
		
		try {
		logger.info("*****TC003_Login_DDT_Execution started*****");
		//Home Page
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
				
		//Login Page
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(email);
		loginPage.setPassword(password);
		loginPage.clickLogin();
				
		//Validations
		MyAccountPage accountPage = new MyAccountPage(driver);
		boolean targetPage = accountPage.targetPage_validation();
		
		LogoutPage logout = new LogoutPage(driver);
		
		//Valid data
		if(expected.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				accountPage.clickLogout();
				logout.clickContinue();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		//Invalid data
		else if(expected.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				accountPage.clickLogout();
				logout.clickContinue();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		logger.info("*****TC003_Login_DDT_Execution completed*****");
		
		
	}

}
