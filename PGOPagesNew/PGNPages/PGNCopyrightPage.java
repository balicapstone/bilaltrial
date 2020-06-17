package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNCopyrightPage extends BasePage{
	
	public LoginHeader header;
	public FooterMenu footer;
	public By supportEmailLink = By.linkText("support@capstonepub.com");
	
	public PGNCopyrightPage(WebDriver driver){
		pageDriver = driver;
		header = new LoginHeader(driver);
		footer = new FooterMenu(driver);
	}
}
