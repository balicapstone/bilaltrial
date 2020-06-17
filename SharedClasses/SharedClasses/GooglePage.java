package SharedClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import UserClasses.User;

public class GooglePage extends BasePage{
	public By email = By.id("identifierId");
	public By nextButton = By.id("identifierNext");
	public By password = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");
	public By passwordNext = By.id("passwordNext");
	
	public GooglePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public GooglePage enterEmail(String username){
		sendKeysToElement(username, email);
		click(nextButton);
		waitImplicitly(3);
		return this;
	}
	
	public GooglePage enterPassword(String pass){
		click(password);
		sendKeysToElement(pass, password);
		waitImplicitly(3);
		return this;
	}
	
	public void login(User user){
		enterEmail(user.getUsername());
		enterPassword(user.getPassword());
		click(passwordNext);
	}
}
