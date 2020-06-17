package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class LoginHeader extends BasePage{
	public By pebbleGoNextLogo = By.xpath("//*[@id='pgo-logo']/a");
	public By aboutTab = By.linkText("About");
	public By FAQTab = By.linkText("FAQ");
	public By systemRequirementsTab = By.linkText("System Requirements");
	public By documentsTab = By.linkText("Documents");
	public By pricingLink = By.linkText("Pricing");
	public By contactUsLink = By.linkText("Contact Us");
	public By[] initialVisibleElements = {pebbleGoNextLogo, aboutTab, FAQTab, systemRequirementsTab, documentsTab,
			pricingLink, contactUsLink};
	
	
	public LoginHeader(WebDriver driver){
		pageDriver = driver;
	}
	
	public SystemRequirementsPage clickSystemRequirementsTab(){
		click(systemRequirementsTab);
		return new SystemRequirementsPage(pageDriver);
	}
	
	public FAQPage clickFAQTab(){
		click(FAQTab);
		return new FAQPage(pageDriver);
	}
	
	public DocumentsPage clickDocumentsTab(){
		click(documentsTab);
		return new DocumentsPage(pageDriver);
	}
	
	public LoginPage clickAboutTab(){
		click(aboutTab);
		return new LoginPage(pageDriver);
	}
	
	public ThirdPartyPage clickPricingLink(){
		click(pricingLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickContactUsLink(){
		click(contactUsLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public LoginPage clickLogo(){
		click(pebbleGoNextLogo);
		return new LoginPage(pageDriver);
	}
}
