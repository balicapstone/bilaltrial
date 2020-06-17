package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuildingsView extends DatabasePage{
	private By createNewBuilding = By.xpath("/html/body/div/div[2]/button");
	private By searchField = By.id("text-search");
	
	public BuildingsView(WebDriver driver) {
		super(driver);
	}
	
	public PurchaseView editPurchase(){
		
		return new PurchaseView(pageDriver);
	}
	
	public BuildingModal createNewBuilding(){
		click(createNewBuilding);
		return new BuildingModal(pageDriver);
	}
	
	public BuildingsView searchForBuilding(String building){
		clearField(searchField);
		sendKeysToElement(building, searchField);
		return this;
	}
	
	public PurchaseView editPurchaseByInt(int i){
		int number = i + 1; 
		pageDriver.findElement(By.xpath("/html/body/div/table/tbody/tr["+number+"]/td[4]/a/button")).click();
		return new PurchaseView(pageDriver);
	}
	
	public DistrictsView clickBackButton(){
		click(back);
		return new DistrictsView(pageDriver);
	}
	

}
