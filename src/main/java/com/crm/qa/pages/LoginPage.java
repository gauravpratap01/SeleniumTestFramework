package com.crm.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	//Initializing page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[text()='Sign Up']")
	WebElement signUp;
	
	@FindBy(xpath="(//img[@class='img-responsive'])[1]")
	WebElement logo;
	
	//Actions
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
		
	}
	public boolean validateCRMLogo()
	{
		return logo.isDisplayed();
	}
	public HomePage login(String uname, String pwd)
	{
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}

}
