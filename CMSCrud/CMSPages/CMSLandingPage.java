package CMSPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import UserClasses.User;

public class CMSLandingPage extends BasePage{
	public By usernameField = By.id("username");
	public By passwordField = By.id("password");
	public By goButton = By.xpath("//*[@id=\"login\"]/div[2]/div[2]/form/fieldset/button");
	
	
	public CMSLandingPage(WebDriver driver){
		pageDriver = driver;
		pageDriver.get("http://cms.pebblego.com");
	}
	
	public CMSLandingPage(WebDriver driver, String environment){
		pageDriver = driver;
		pageDriver.get("http://"+environment+".pebblego.com");
	}
	
	public CMSHomePage login(User u){
		this.click(usernameField);
		this.getDriver().findElement(usernameField).sendKeys(u.getUsername());
	
		this.click(passwordField);
		this.getDriver().findElement(passwordField).sendKeys(u.getPassword());

		this.pressEnter(goButton);
		
		//click(goButton);
		return new CMSHomePage(u.getDriver());
	}
}
