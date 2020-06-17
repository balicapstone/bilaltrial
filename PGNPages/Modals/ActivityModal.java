package Modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.ThirdPartyPage;


public class ActivityModal extends BaseModal{
	public By printButton = By.linkText("Print");
	public By activeTab = By.xpath("//*[@id=\"modal-container\"]/div/div/div[1]//a[contains(@class,'active')]");
	
	public ActivityModal(WebDriver driver){
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
