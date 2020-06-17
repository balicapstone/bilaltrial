package CITests.CIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class StudentEulaPopUp extends BasePage{

	public By closeButton = By.id("close_eula_error");
	public By errorMessage = By.xpath("//*[@id=\"eula\"]/h3");
	
	public StudentEulaPopUp(WebDriver driver){
		pageDriver = driver;
	}
	
	public void clickClose(){
		click(closeButton);
	}
}
