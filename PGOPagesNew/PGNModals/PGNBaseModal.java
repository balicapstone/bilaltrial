package PGNModals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNBaseModal extends BasePage{
	// public By closeButton = By.xpath("//*[@id=\"modal-container\"]//span[@class='close']");
	
	public By closeButton = By.xpath("//*[@id=\"close-button\"]");
	
	public By title = By.xpath("//*[@id=\"modal-container\"]//*[@class='close']");
	//*[@id="modal-container"]/div/div/span
	
	public PGNBaseModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public String getTitle(){
		return pageDriver.findElement(title).getText();
	}
	
	//Void because it isn't creating a new page and just removing this element
	public void closeModal(){
		getModalElement(closeButton).click();
	}
}
