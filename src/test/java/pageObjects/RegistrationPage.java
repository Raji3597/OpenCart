package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;
	
	@FindBy(xpath="//input[@name='newsletter' and @value='1']")
	WebElement rdoSubscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//div[@id='content']//h1")
	WebElement confirmMessage;
	
	public void setFirstName(String firstname)
	{
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname)
	{
		txtLastName.sendKeys(lastname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String phoneNo)
	{
		txtTelephone.sendKeys(phoneNo);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setPasswordConfirm(String password)
	{
		txtPasswordConfirm.sendKeys(password);
	}
	
	public void clickSubscribe()
	{
		rdoSubscribe.click();
	}
	
	public void clickPrivacyPolicy()
	{
		chkPrivacyPolicy.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public String getConfirmationMessage()
	{
		String message;
		try
		{
			message = confirmMessage.getText();
		}
		catch(Exception e)
		{
			message = e.getMessage();
		}
		
		return message;
	}

}
