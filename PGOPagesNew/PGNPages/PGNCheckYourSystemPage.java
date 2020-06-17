package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNCheckYourSystemPage extends BasePage{

	public By browserName;
	public By browserVersion;
	public By platformName;
	public By browserCheck;
	public By javascriptCheck;
	
	public PGNCheckYourSystemPage(WebDriver driver){
		pageDriver = driver;
	}
}
