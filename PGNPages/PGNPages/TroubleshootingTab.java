package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class TroubleshootingTab extends BasePage{
	public By systemRequirementsLink = By.linkText("system requirements");
	public By firstCustomerServiceLink = By.xpath("//*[@id=\"container\"]/div/div/div/div[2]/p[3]/a");
	public By secondCustomerServiceLink = By.xpath("//*[@id=\"container\"]/div/div/div/div[2]/p[4]/a");
	
	public TroubleshootingTab(WebDriver driver){
		pageDriver = driver;
	}
	
	public SystemRequirementsPage systemRequirementsLink(){
		click(systemRequirementsLink);
		return new SystemRequirementsPage(pageDriver);
	}
}
