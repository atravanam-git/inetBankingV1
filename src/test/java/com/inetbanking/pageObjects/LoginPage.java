package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
WebDriver ldriver;
	
//	Constructor
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
//	Finding Web Elements with @FindBy annotation
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement lnkLogout;
	
	
//	Passing user name to the web Element txtUserName
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
//	Passing password to the web Element txtPassword
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
//	Clicking Login Button
	
	public void clickSubmit()
	{
		btnLogin.click();
	}
	
//	Clicking Logout Button
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
	

}
