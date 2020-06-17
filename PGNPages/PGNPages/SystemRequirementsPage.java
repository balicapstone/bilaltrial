package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class SystemRequirementsPage extends BasePage{
	public LoginHeader header;
	public FooterMenu footer;
	public By checkYourSystemLink = By.linkText("Check your system?");
	
	public SystemRequirementsPage(WebDriver driver){
		pageDriver = driver;
		header = new LoginHeader(pageDriver);
		footer = new FooterMenu(pageDriver);
	}
	
	public CheckYourSystemPage clickCheckYourSystemLink(){
		click(checkYourSystemLink);
		return new CheckYourSystemPage(pageDriver);
	}
}
