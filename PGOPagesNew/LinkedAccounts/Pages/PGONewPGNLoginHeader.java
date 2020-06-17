package LinkedAccounts.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.PGONewBasePage;

public class PGONewPGNLoginHeader extends PGONewBasePage{
	public By pebbleGoNextLogo = By.xpath("//*[@id='pgo-logo']/a");
	public By aboutTab = By.linkText("About");
	public By FAQTab = By.linkText("FAQ");
	public By systemRequirementsTab = By.linkText("System Requirements");
	public By documentsTab = By.linkText("Documents");
	public By pricingLink = By.linkText("Pricing");
	public By contactUsLink = By.linkText("Contact Us");
	public By[] initialVisibleElements = {pebbleGoNextLogo, aboutTab, FAQTab, systemRequirementsTab, documentsTab,
			pricingLink, contactUsLink};
	
	
	public PGONewPGNLoginHeader(WebDriver driver){
		pageDriver = driver;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
