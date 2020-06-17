package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class NavigationBar extends BasePage{
	public By pricingButton = By.id("btn-subscrInfo");
	public By contactUsButton = By.id("btn-contact");
	public By aboutButton = By.id("btn-learnMore");
	public By faqButton = By.id("btn-faq");
	public By systemRequirementsButton = By.id("btn-sysReq");
	public By documentsButton = By.id("btn-documents"); 
	public By PebbleGoLogo = By.id("logo");
	public By[] initialVisibleElements = {pricingButton, contactUsButton, aboutButton, faqButton, systemRequirementsButton, 
			documentsButton, PebbleGoLogo};
	
	public NavigationBar(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	/*
	 * Class click element methods
	 */
	public ThirdPartyPage clickPricingButton() {
		click(pricingButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickContactUsButton(){
		click(contactUsButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public FAQPage clickFAQButton(){
		click(faqButton);
		return new FAQPage(pageDriver);
	}
	
	public SystemRequirementsPage clickSystemRequirementsButton(){
		click(systemRequirementsButton);
		return new SystemRequirementsPage(pageDriver);
	}
	
	public DocumentsPage clickDocumentsButton(){
		click(documentsButton);
		return new DocumentsPage(pageDriver);
	}
	
	public LoginPage clickPebbleGoLogo(){
		click(PebbleGoLogo);
		return new LoginPage(pageDriver);
	}
	
	public LoginPage clickAboutButton(){
		click(aboutButton);
		return new LoginPage(pageDriver);
	}	
}
