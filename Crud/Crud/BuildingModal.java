package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class BuildingModal extends BasePage{
	private By buildingNameField = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[1]/input");
	private By salesforceIDField = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[2]/input");
	private By createNewBuildingButton = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/button");
	private By close = By.xpath("/html/body/div/div[4]/div[2]/div[1]");
	
	public BuildingModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public BuildingModal enterBuildingName(String name){
		clearField(buildingNameField);
		sendKeysToElement(name, buildingNameField);
		return this;
	}
	
	public BuildingModal enterSalesforceId(String id){
		clearField(salesforceIDField);
		sendKeysToElement(id, salesforceIDField);
		return this;
	}
	
	public BuildingModal createNewBuildingError(){
		click(createNewBuildingButton);
		return this;
	}
	
	public BuildingsView createNewBulding(){
		click(createNewBuildingButton);
		return new BuildingsView(pageDriver);
	}
	
	public BuildingsView closeModal(){
		click(close);
		return new BuildingsView(pageDriver);
	}
}
