package SuperAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class AccountListPage extends BasePage{
	public By searchField = By.xpath("//*[@id=\"ui-accordion-accordion-panel-0\"]/form/ul/li[1]/input");
	public By applyFilterButton = By.xpath("//input[@value=\"Apply filter\"]");
	public By disableAccountButton = By.xpath("//a[@title=\"Disable this account\"]/span");
	public By enableAccountButton = By.xpath("//a[@title=\"Enable this account\"]/span");
	public By modalOKButton = By.xpath("//span[contains(text(), 'OK')]");
	public By viewBuildingsButton = By.xpath("//a[@title=\"See buildings in this account\"]/span[2]");
	
	public AccountListPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public void searchForAccount(String account){
		sendKeysToElement(account, searchField);
	}
	
	public void clickFilterButton(){
		click(applyFilterButton);
	}
	
	public void disableAccount(){
		click(disableAccountButton);
		click(modalOKButton);
	}
	
	public void enableAccount(){
		click(enableAccountButton);
		click(modalOKButton);
	}
	
	public BuildingListPage viewBuildings(){
		click(viewBuildingsButton);
		return new BuildingListPage(pageDriver);
	}
}
