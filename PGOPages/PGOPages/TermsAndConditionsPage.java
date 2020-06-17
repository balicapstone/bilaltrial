package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class TermsAndConditionsPage extends BasePage{

	private By copyrightInfringementLink = By.linkText("Notice and Procedure for Making Claims of Copyright Infringement");
	private By firstPrivacyPolicyLink = By.xpath("//*[@id=\"content\"]/div[1]/ol[1]/li[12]/a");
	private By secondPrivacyPolicyLink = By.xpath("//*[@id=\"content\"]/div[1]/ol[1]/li[14]/a");
	private By pebbleGoEmail = By.linkText("support@capstonepub.com");
	private By[] initialVisibleElements = {copyrightInfringementLink,firstPrivacyPolicyLink,secondPrivacyPolicyLink,pebbleGoEmail}; 
	public NavigationBar navBar;
	
	public TermsAndConditionsPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	/**
	 * Verify that the PebbleGo email element is displayed.
	 * @return
	 */
	public boolean verifyPebbleGoEmail(){
		return pageDriver.findElement(pebbleGoEmail).isDisplayed();
	}
	
	/*
	 * Class click element methods.
	 */
	public CopyrightPage clickCopyrightInfringementLink(){
		this.scrollToElement(pageDriver.findElement(copyrightInfringementLink));
		pageDriver.findElement(copyrightInfringementLink).click();
		return new CopyrightPage(pageDriver);
	}
	
	public PrivacyPolicyPage clickFirstPrivacyPolicyLink(){
		this.scrollToElement(pageDriver.findElement(firstPrivacyPolicyLink));
		pageDriver.findElement(firstPrivacyPolicyLink).click();
		return new PrivacyPolicyPage(pageDriver);
	}
	
	public PrivacyPolicyPage clickSecondPrivacyPolicyLink(){
		this.scrollToElement(pageDriver.findElement(secondPrivacyPolicyLink));
		pageDriver.findElement(secondPrivacyPolicyLink).click();
		return new PrivacyPolicyPage(pageDriver);
	}
}
