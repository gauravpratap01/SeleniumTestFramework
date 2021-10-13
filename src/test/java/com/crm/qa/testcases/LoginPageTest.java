package com.crm.qa.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginPageTest() {
		super(); // to call the base class constructor, which will initialize the property file
	}
	
	@BeforeMethod
	public void setup() {
		initialization();	
		loginpage=new LoginPage();
	}
	
	//All the Tests should be independent of each other. It's good practice to user different browser
	//Otherwise Browser will get exhausted
	//Before each test case- Launch the browser and Login
	//After each test case- Close the browser
	
	@Test(priority=1)
	public void titleTest() {
		String title=loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void crmLogoTest()
	{
		boolean flag=loginpage.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest()
	{
		homepage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
