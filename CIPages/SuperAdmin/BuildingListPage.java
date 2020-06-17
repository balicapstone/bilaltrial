package SuperAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class BuildingListPage extends BasePage{
	public By buildingSearch = By.id("building-filter-building");
	public By applyFilterButton = By.xpath("//input[@value=\"Apply filter\"]");
	public By editSchoolButton = By.xpath("//td[@class=\"grid-actions\"]/a[1]");
	public By disableSchoolButton = By.xpath("//a[@title=\"Disable this building\"]/span");
	public By enableSchoolButton = By.xpath("//a[@title=\"Enable this building\"]/span");
	public By modalOKButton = By.xpath("//span[contains(text(), 'OK')]");
	
	public BuildingListPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public void searchForBuilding(String building){
		clearField(buildingSearch);
		sendKeysToElement(building, buildingSearch);
	}
	
	public void clickFilterButton(){
		click(applyFilterButton);
	}
	
	public EditBuildingPage editBuilding(){
		click(editSchoolButton);
		return new EditBuildingPage(pageDriver);
	}
	
	public void disableBuilding(){
		click(disableSchoolButton);
		click(modalOKButton);
	}
	
	public void enableBuilding(){
		click(enableSchoolButton);
		click(modalOKButton);
	}
}
