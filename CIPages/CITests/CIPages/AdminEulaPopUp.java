package CITests.CIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class AdminEulaPopUp extends BasePage{
	public By EulaLink = By.xpath("//*[@id=\"eula\"]/div/a");
	public By AcceptButton = By.id("eula-form");
	public By messageText = By.xpath("//*[@id=\"eula\"]/h3");
	
	public AdminEulaPopUp(WebDriver driver){
		pageDriver = driver;
	}
	
	public void ClickEULALink(){
		click(EulaLink);
	}
	
	public void AcceptEula(){
		click(AcceptButton);
	}
}
