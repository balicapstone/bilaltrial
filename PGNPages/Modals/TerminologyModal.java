package Modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TerminologyModal extends BaseModal{
	public By title = By.xpath("//*[@id=\"modal-container\"]/div/h3");
	public By explanation = By.xpath("//*[@id=\"modal-container\"]/div/p");
	
	public TerminologyModal(WebDriver driver){
		super(driver);
	}
	
	public String getModalTitle(){
		return getElementText(title);
	}
	
	public String getModalText(){
		return getElementText(explanation);
	}
}
