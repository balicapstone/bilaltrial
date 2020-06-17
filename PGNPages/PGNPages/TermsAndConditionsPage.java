package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class TermsAndConditionsPage extends BasePage{
	public By copyrightInfringementLink = By.linkText("Notice and Procedure for Making Claims of Copyright Infringement");
	public By firstPrivacyPolicyLink = By.xpath("//*[@id=\"text-container\"]/div[2]/ol[1]/li[12]/a");
	public By secondPrivacyPolicyLink = By.xpath("//*[@id=\"text-container\"]/div[2]/ol[1]/li[14]/a");
	public By supportEmailLink = By.linkText("support@capstonepub.com");
	public LoginHeader header;
	public FooterMenu footer;
	public By[] initialVisibleElements = {copyrightInfringementLink, firstPrivacyPolicyLink, secondPrivacyPolicyLink, supportEmailLink};
	
	public TermsAndConditionsPage(WebDriver driver){
		pageDriver = driver;
		header = new LoginHeader(driver);
		footer = new FooterMenu(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
