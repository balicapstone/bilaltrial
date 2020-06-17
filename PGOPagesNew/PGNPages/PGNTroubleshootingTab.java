package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNTroubleshootingTab extends BasePage{
	public By systemRequirementsLink = By.linkText("system requirements");
	public By firstCustomerServiceLink = By.xpath("//*[@id=\"container\"]/div/div/div/div[2]/p[3]/a");
	public By secondCustomerServiceLink = By.xpath("//*[@id=\"container\"]/div/div/div/div[2]/p[4]/a");
	
	public PGNTroubleshootingTab(WebDriver driver){
		pageDriver = driver;
	}
	
	public PGNSystemRequirementsPage systemRequirementsLink(){
		click(systemRequirementsLink);
		return new PGNSystemRequirementsPage(pageDriver);
	}
}
