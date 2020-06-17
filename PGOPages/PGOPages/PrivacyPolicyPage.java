package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PrivacyPolicyPage extends BasePage{
	private By termsOfUseLink = By.linkText("Terms of Use");
	private By supportEmailLink = By.linkText("support@capstonepub.com");
	private By[] initialVisibleElements = {termsOfUseLink, supportEmailLink};
	public NavigationBar navBar;
	
	public PrivacyPolicyPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	/* Class click element method */
	public TermsAndConditionsPage clickTermsAndConditionsLink(){
		pageDriver.findElement(termsOfUseLink).click();
		return new TermsAndConditionsPage(pageDriver);
	}
}
