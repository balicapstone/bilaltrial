package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class FooterMenu extends BasePage{

	public By capstoneWatermark = By.xpath("//footer/div/img");
	//html/body/footer/div/img
	public By termsAndConditionsLink = By.linkText("TERMS & CONDITIONS");
	public By privacyPolicyLink = By.linkText("PRIVACY POLICY");
	public By aboutUsLink = By.linkText("ABOUT US");
	public By creditsLink = By.linkText("CREDITS");
	public By copyrightLink = By.linkText("COPYRIGHT");
	public By capstoneCopyright = By.xpath("//footer/nav/ul/li[6]");
	public By[] initialVisibleElements = {capstoneWatermark, termsAndConditionsLink, privacyPolicyLink, aboutUsLink, creditsLink, 
			copyrightLink, capstoneCopyright};
	
	public FooterMenu(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public TermsAndConditionsPage clickTermsAndConditionsLink(){
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
	
	public CopyrightPage clickCopyrightPage(){
		click(copyrightLink);
		return new CopyrightPage(pageDriver);
	}
	
	
}
