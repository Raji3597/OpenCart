package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups= {"regression", "master"})
	public void verifyAccountRegistration()
	{
		logger.info("-------TC001_Execution started -------");
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount");
		homePage.clickRegister();
		logger.info("Clicked on Register");
		
		logger.info("Enter customer details");
		RegistrationPage regPage = new RegistrationPage(driver);
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setTelephone(randomNumeric());
		
		String password = randomAlphanumeric();
		regPage.setPassword(password);
		regPage.setPasswordConfirm(password);
		regPage.clickSubscribe();
		regPage.clickPrivacyPolicy();
		regPage.clickContinue();
		
		//Validations
		if(regPage.getConfirmationMessage().equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("Validation successful");
		}
		else
		{
			logger.error("Validation failed");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		logger.info("---------TC001_Exceution Completed------");
		//Assert.assertEquals(regPage.getConfirmationMessage(), "Your Account Has Been Created!");
		
	}
	
	

}









