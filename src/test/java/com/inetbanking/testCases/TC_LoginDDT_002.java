package com.inetbanking.testCases;
/* 
 *** There will be two methods  in this class
 *** 1. @Test, loginDDT() - to login with different test data
 *** 2. @Dataprovider, getData() - To fetch data from excel and return the array 
 */
import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.Utilities.XLUtils;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass{
//	The below method takes the values returned from the dat provider LoginData
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
//		If there is an ALERT after login then it return false
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
//		If there is an ALERT after login then it return true
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			
		}
		
		
	}
	
//	Since this is commonly used we can push it to BaseClass too
	public boolean isAlertPresent() //user defined method created to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
//	@Dataprovider, always takes an unique name, in this case LoginData
//	getData() method will fetch data from excel and return the String two dim array to loginDDT()
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
//		Tow dimensional array
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
//				since we deleted 1st row in excel which is usually column headers
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1st row & 0th column
			}
				
		}
	return logindata;
	}

}
