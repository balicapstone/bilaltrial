package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class UpdateEulasModal extends BasePage{
	//private By appDropdown = By.xpath("");
	//private By attachedEula = By.xpath("");
	private By massUpdateButton = By.xpath("");
	
	public UpdateEulasModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public PurchaseView massUpdate(){
		click(massUpdateButton);
		return new PurchaseView(pageDriver);
	}
	
	
}
