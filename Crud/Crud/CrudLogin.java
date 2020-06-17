package Crud;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;
import UserClasses.User;



public class CrudLogin extends BasePage{

	public String environment = "qapi";
	public String browser = "firefox";
	
	public By submitButton = By.xpath("/html/body/div/form/span/div[3]/div/input");
	public By usernameField = By.id("username");
	public By passwordField = By.id("password");
	
	public CrudLogin(User u){
			pageDriver = u.getDriver();
		
		
			pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			//this.maximizeWindow();
			//resizeWindow(1200, 1280);
			
			//Adding to run single class tests for debugging without using the JunitTestSuiteRunner
			if(environment == null){
				environment = "www";
			}
			
			try{
				pageDriver.get("https://"+environment+".pivoted.com/adminLogin");
				//pageDriver.get("https://www.pebblego.com");
			} catch(Exception e){
				pageDriver.close();
			}
	}
	
	public CrudLogin(WebDriver driver){
		pageDriver = driver;
	}
	
	
	public DatabaseSelect login(User user){
		sendUsername(user.getUsername());
		sendPassword(user.getPassword());
		return submitCredentials();
	}
	
	public CrudLogin sendUsername(String username){
		sendKeysToElement(username, usernameField);
		return this;
	}
	
	public CrudLogin sendPassword(String password){
		sendKeysToElement(password, passwordField);
		return this;
	}
	
	public DatabaseSelect submitCredentials(){
		click(submitButton);
		return new DatabaseSelect(pageDriver);
	}
}
