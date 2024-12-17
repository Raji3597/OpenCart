package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement lnkLogout;
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement targetPageHeading;
	
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
	public boolean targetPage_validation()
	{
		if(targetPageHeading.isDisplayed())
			return true;
		else
			return false;
	}

}
