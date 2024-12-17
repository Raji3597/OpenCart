package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;
    
	@BeforeClass(groups= {"sanity", "regression", "master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException
	{
		logger = LogManager.getLogger(this.getClass()); //this.getClass() - returns the current running class (since we can't specify the class name everytime)
														//this returns the log4j2.xml file to logger variable
		
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			switch (os.toLowerCase())
			{
			case "windows" : capabilities.setPlatform(Platform.WIN11); break;
			case "mac" : capabilities.setPlatform(Platform.MAC); break;
			case "linux" : capabilities.setPlatform(Platform.LINUX); break;
			default: System.out.println("No matching os found"); return;
			}
			
			//browser
			switch(browser.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("No matching browser found"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		else if (properties.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(browser.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name"); return; //return will terminate from this method
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(properties.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups= {"sanity", "regression", "master"})
	public void teardown()
	{
		driver.quit();
	}


	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomAlphanumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return(generatedString+generatedNumber);
	}
	
	
	public String randomNumeric()
	{
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}



}
