package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class DistrictModal extends BasePage{
	private By districtName = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[1]/input");
	private By zipcode = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[2]/input");
	private By salesforceID = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[3]/input");
	private By createNewDistrict = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/button");
	
	public DistrictModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public DistrictModal enterDistrictName(String district){
		clearField(districtName);
		sendKeysToElement(district, districtName);
		return this;
	}
	
	public DistrictModal enterZipCode(String zip){
		clearField(zipcode);
		sendKeysToElement(zip, zipcode);
		return this;
	}
	
	public DistrictModal enterSalesForceID(String sales){
		clearField(salesforceID);
		sendKeysToElement(sales, salesforceID);
		return this;
	}
	
	public DistrictsView createDistrict(){
		click(createNewDistrict);
		return new DistrictsView(pageDriver);
	}
	
	public DistrictModal createErrorsExpected(){
		click(createNewDistrict);
		return this;
	}
}
