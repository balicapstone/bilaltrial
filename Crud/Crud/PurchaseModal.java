package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import SharedClasses.BasePage;

public class PurchaseModal extends BasePage{
	private By attachedEULADropdown;
	private By appDropdown;
	private By purchaseTypeDropdown;
	private By orderNumberField = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[4]/input");
	private By activationDateField = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[5]/div/datepicker/input");
	private By expirationDateField = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/div[6]/div/datepicker/input");
	private By createNewPurchaseButton = By.xpath("/html/body/div/div[4]/div[2]/div[2]/div/button");//("/html/body/div/div[4]/div[2]/div[2]/div/button");
	private By close = By.cssSelector("body > div > div:nth-child(7) > div.ng-modal-dialog > div.ng-modal-close");
	
	public PurchaseModal(WebDriver driver){
		pageDriver = driver;
		attachedEULADropdown = By.id("eulaSelect");
		appDropdown = By.id("appSelect");
		purchaseTypeDropdown = By.id("purchaseSelect");
	}
	
	public PurchaseView createNewPurchaseButton(){
		click(createNewPurchaseButton);
		return new PurchaseView(pageDriver);
	}
	
	public PurchaseModal createNewPurchaseError(){
		click(createNewPurchaseButton);
		return this;
	}
	
	public PurchaseModal enterActivationDate(String date){
		clearField(activationDateField);
		sendKeysToElement(date, activationDateField);
		return this;
	}
	
	public PurchaseModal enterExpirationDate(String date){
		clearField(expirationDateField);
		sendKeysToElement(date, expirationDateField);
		return this;
	}
	
	public PurchaseModal enterOrderNumber(String data){
		clearField(orderNumberField);
		sendKeysToElement(data, orderNumberField);
		return this;
	}
	
	public PurchaseView close(){
		click(close);
		return new PurchaseView(pageDriver);
	}
	
	public PurchaseModal selectEULA(String eula){
		//click(attachedEULADropdown);
		//sendKeysToElement(eula, attachedEULADropdown);
		int numb = pageDriver.findElements(attachedEULADropdown).size();
		
		
		Select current = new Select(pageDriver.findElements(attachedEULADropdown).get(numb-2));
		
		//List<WebElement> web = current.getOptions();
		current.selectByVisibleText(eula);
		//selectDropdownOptionByText(attachedEULADropdown, eula);
		return this;
	}
	
	public PurchaseModal selectAppType(String app){
		int numb = pageDriver.findElements(appDropdown).size();
		
		Select current = new Select(pageDriver.findElements(appDropdown).get(numb-2));
		current.selectByVisibleText(app);
		//selectDropdownOptionByText(appDropdown, app);
		return this;
	}
	
	public PurchaseModal selectPurchaseType(String purchase){
		int numb = pageDriver.findElements(purchaseTypeDropdown).size();

		Select current = new Select(pageDriver.findElements(purchaseTypeDropdown).get(numb-1));
		current.selectByVisibleText(purchase);
		//selectDropdownOptionByText(purchaseTypeDropdown, purchase);
		return this;
	}
}
