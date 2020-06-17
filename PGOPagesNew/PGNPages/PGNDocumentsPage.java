package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNDocumentsPage extends BasePage{
	
	public By statesContent = By.linkText("States & American Indians Content");
	public By scienceContent = By.linkText("Science Content");
	public By multimodalLiteracy = By.linkText("PebbleGo and Multimodal Literacy");
	public FooterMenu footer;
	public LoginHeader header;
	public By[] initialVisibleElements = {statesContent, scienceContent, multimodalLiteracy};
	
	public PGNDocumentsPage(WebDriver driver){
		pageDriver = driver;
		
		header = new LoginHeader(driver);
		footer = new FooterMenu(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
