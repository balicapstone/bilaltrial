package LearnMorePages;

import org.openqa.selenium.By;

import CITests.CIPages.CILoginPage;
import SharedClasses.BasePage;

public class LearnMoreBasePage extends BasePage{
	public By aboutButton = By.xpath("//*[@id=\"main-navigation\"]/ul/li[1]/a");
	public By demoButton = By.xpath("//*[@id=\"main-navigation\"]/ul/li[2]/a");
	public By orderButton = By.xpath("//*[@id=\"main-navigation\"]/ul/li[3]/a");//By.linkText("Order");
	public By contactUsTop = By.id("contact-us");
	public By capstoneLogoTop = By.id("capstone-interactive");
	public By capstoneLogoBottom = By.id("capstone-logo");
	public By revisionText = By.id("revision");
	public By aboutCapstoneLink = By.linkText("About Capstone");
	public By privacyPolicyLink = By.linkText("Privacy Policy");
	public By termsOfUseLink = By.linkText("Terms of Use");
	public By licenseAgreementLink = By.linkText("License Agreement");
	public By contactUsLink = By.linkText("Contact Us");
	public By educatorsLink = By.linkText("Educators");
	public By contactUsBottom = By.xpath("//*[@id='footer-nav']/ul/li[5]/a");
	
	
	public LearnMoreDemoPage clickDemoButton(){
		click(demoButton);
		return new LearnMoreDemoPage(pageDriver);
	}
	
	public LearnMoreOrderPage clickOrderButton(){
		click(orderButton);
		return new LearnMoreOrderPage(pageDriver);
	}
	
	public LearnMoreAboutPage clickAboutButton(){
		click(aboutButton);
		return new LearnMoreAboutPage(pageDriver);
	}
	
	public PrivacyPolicyPage clickPrivacyPolicyLink(){
		click(privacyPolicyLink);
		return new PrivacyPolicyPage(pageDriver);
	}
	
	public TermsOfUsePage clickTermsOfUseLink(){
		click(termsOfUseLink);
		return new TermsOfUsePage(pageDriver);
	}
	
	public EducatorsPage clickEducatorsLink(){
		click(educatorsLink);
		return new EducatorsPage(pageDriver);
	}
	
	public CILoginPage clickCapstoneLogoTop(){
		click(capstoneLogoTop);
		return new CILoginPage(pageDriver);
	}
}
