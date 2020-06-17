package PGNModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.ThirdPartyPage;


public class PGNCitationsModal extends PGNBaseModal{
	public By printAPAButton = By.linkText("Print APA");
	public By printMLAButton = By.linkText("Print MLA");
	public By printCMSButton = By.linkText("Print CMS");
	
	public PGNCitationsModal(WebDriver driver){
		super(driver);
	}
	
	public ThirdPartyPage printAPA(){
		click(printAPAButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage printMLA(){
		click(printMLAButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage printCMS(){
		click(printCMSButton);
		return new ThirdPartyPage(pageDriver);
	}
}
