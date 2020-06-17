package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNPrivacyPolicyPage extends BasePage{
	
	public By termsOfUseLink = By.linkText("Terms of Use");
	public By supportEmailLink = By.linkText("support@capstonepub.com");
	public LoginHeader header;
	public FooterMenu footer;
	public By[] initialVisibleElements = {termsOfUseLink, supportEmailLink};
	
	public PGNPrivacyPolicyPage(WebDriver driver){
		pageDriver = driver;
		header = new LoginHeader(driver);
		footer = new FooterMenu(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
