package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class DistrictsView extends BasePage{
	
	private By logout = By.id("logout");
	private By backButton = By.xpath("/html/body/div/a/button");
	private By newDistrict = By.xpath("/html/body/div/div[2]/button");
	private By searchField = By.id("text-search");
	
	public DistrictsView(WebDriver driver){
		pageDriver = driver;
	}
	
	public CrudLogin logout(){
		click(logout);
		return new CrudLogin(pageDriver);
	}
	
	public DatabaseSelect goBackToDatabaseSelect(){
		click(backButton);
		return new DatabaseSelect(pageDriver);
	}
	
	public DistrictsView search(String query){
		clearField(searchField);
		sendKeysToElement(query, searchField);
		return this;
	}
	
	public DistrictModal openNewDistrictModal(){
		click(newDistrict);
		return new DistrictModal(pageDriver);
	}
	
	public BuildingsView showBuildingsByIndex(int i){
		int number = i+1;
		pageDriver.findElement(By.xpath("/html/body/div/table/tbody/tr["+ number +"]/td[5]/a/button")).click();
		
		return new BuildingsView(pageDriver);
	}
	
}
