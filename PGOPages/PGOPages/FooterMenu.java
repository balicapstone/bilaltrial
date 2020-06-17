package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class FooterMenu extends BasePage{
	public By termsAndConditionsLink = By.linkText("Terms and Conditions");//("//*[@id=\"footer-menu\"]/ul/li[1]/a");
	public By privacyPolicyLink = By.linkText("Privacy Policy");
	public By aboutUsLink = By.linkText("About Us");//By.xpath("//*[@id=\"footer-menu\"]/ul/li[3]/a");
	public By creditsLink = By.linkText("Credits");
	public By copyrightLink = By.linkText("Copyright"); //By.xpath("//*[@id=\"footer-menu\"]/ul/li[5]/a");
	public By[] initialVisibleElements = {termsAndConditionsLink, privacyPolicyLink, aboutUsLink, creditsLink, copyrightLink};
	
	public FooterMenu(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	/*
	 * Class click element methods.
	 */
	public TermsAndConditionsPage clickTermsAndConditionLink(){
		click(termsAndConditionsLink);
		return new TermsAndConditionsPage(pageDriver);
	}
	
	public PrivacyPolicyPage clickPrivacyPolicyLink(){
		click(privacyPolicyLink);
		return new PrivacyPolicyPage(pageDriver);
	}
	
	public AboutUsPage clickAboutUsLink(){
		click(aboutUsLink);
		return new AboutUsPage(pageDriver);
	}
	
	public CreditsPage clickCreditsLink(){
		click(creditsLink);
		return new CreditsPage(pageDriver);
	}
	
	public CopyrightPage clickCopyrightLink(){
		click(copyrightLink);
		return new CopyrightPage(pageDriver);
	}
}
