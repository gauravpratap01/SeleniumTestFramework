package com.crm.qa.testcases;
import java.net.URISyntaxException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	TestUtil testUtil;
	LoginPage loginPage;
	HomePage homePage;	
	ContactsPage contactsPage;
	String sheetName="Contacts";
	
	public ContactsPageTest() {
		//calling parent class constructor for git
		super();
	}
	
	@BeforeMethod
	public void setup() {
		//calling base class method
		initialization();
		contactsPage=new ContactsPage();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame("mainpanel");
		//contactsPage=homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		contactsPage=homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactsLable(),"Contacts label is missing");
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage=homePage.clickOnContactsLink();
		contactsPage.selectContactsByName("Test2 Test2");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage=homePage.clickOnContactsLink();
		contactsPage.selectContactsByName("Ui Ui");
		contactsPage.selectContactsByName("Test2 Test2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DataProvider
	public Object[][] getNewContactsData() throws URISyntaxException {
		Object[][] data=TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4,dataProvider="getNewContactsData")
	public void validateCreateNewContactTest(String title,String fname,String lname,String comp) {
		
		homePage.clickOnNewContactsLink();
		//Below comments is when you have hard coded the value
		//contactsPage.createNewContact("Mr.","Tom","Peter","Google");
		
		//Below one is using data provider through excel
		contactsPage.createNewContact(title, fname, lname, comp);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
