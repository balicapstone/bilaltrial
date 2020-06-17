package PGNModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.ThirdPartyPage;


public class PGNActivityModal extends PGNBaseModal{
	// public By printButton = By.linkText("Print");
	
	public By printButton = By.xpath("//*[@id=\"root\"]/div/div[2]/main/div/div[1]/div[2]/button");
	public By activeTab = By.xpath("//*[@id=\"modal-container\"]/div/div/div[1]//a[contains(@class,'active')]");
	
	public PGNActivityModal(WebDriver driver){
		super(driver);
	}
	
	public ThirdPartyPage print(){
		click(printButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public String getActiveTab(){
		waitForElement(activeTab);
		return this.getModalElement(activeTab).getText().toString();
	}
}
