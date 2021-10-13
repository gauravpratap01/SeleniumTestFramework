package com.crm.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="//td[contains(text(),'User: Gaurav Singh')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement newContactsLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	public void clickOnNewContactsLink() {
		Actions action=new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		//Thread.sleep(2000);
		//action.moveToElement(newContactsLink).build().perform();
		//Thread.sleep(2000);
		newContactsLink.click();
		
	}

}
