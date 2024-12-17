package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\TestData_Login.xlsx"; 
		ExcelUtility xlutil = new ExcelUtility(path); //creating object for ExcelUtility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcells = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String [totalrows][totalcells]; //created 2 dimensional array which can store the data same size as excel sheet
		
		for(int i=1; i<=totalrows; i++)
		{
			for(int j=0; j<totalcells; j++)
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //i-1 => since in array index starts from 0
			}
		}
		
		return logindata;
	}

}
