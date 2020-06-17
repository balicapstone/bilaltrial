package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import UserClasses.User;

public class LoginModal extends BasePage{
	public By usernameField = By.id("username");
	public By passwordField = By.id("password");
	public By goButton = By.xpath("//*[@id=\"login\"]/footer/input");
	
	public LoginModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public CMSHomePage login(User u){
		this.sendKeysToElement(u.getUsername(), usernameField);
		this.sendKeysToElement(u.getPassword(), passwordField);
		click(goButton);
		return new CMSHomePage(u.getDriver());
	}
}
