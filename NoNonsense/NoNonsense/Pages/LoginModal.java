package NoNonsense.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import UserClasses.User;

public class LoginModal extends BasePage{
	protected By username = By.id("username");
	protected By password = By.id("password");
	protected By goButton = By.xpath("//*[@id=\"login\"]/footer/input");
	
	public LoginModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public SearchPage login(User u){
		sendKeysToElement(u.getUsername(), username);
		sendKeysToElement(u.getPassword(), password);
		click(goButton);
		return new SearchPage(pageDriver);
	}
	
	public LoginModal loginError(User u){
		sendKeysToElement(u.getUsername(), username);
		sendKeysToElement(u.getPassword(), password);
		click(goButton);
		return this;
	}
	
	public EulaModal loginToSignEula(User u){
		sendKeysToElement(u.getUsername(), username);
		sendKeysToElement(u.getPassword(), password);
		click(goButton);
		return new EulaModal(this.getDriver());
	}
}
