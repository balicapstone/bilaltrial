package CITests.CIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SharedClasses.BasePage;
import SharedClasses.GooglePage;
import UserClasses.User;

public class CILoginModal extends BasePage{
	public By pgologin = By.xpath("//*[@id=\"select-product\"]/div[1]");
	public By capstoneLogin = By.xpath("//*[@id=\"select-product\"]/div[2]");
	public By googleLogin = By.xpath("//*[@id=\"select-product\"]/a");
	public By username = By.xpath("//*[@id=\"login\"]/div[3]/form/div[1]/input");
	public By password = By.xpath("//*[@id=\"login\"]/div[3]/form/div[2]/input");
	public By loginButton = By.xpath("//*[contains(@class, 'rounded-submit')]");
	public By errorMessage = By.xpath("//*[@id=\"login\"]/div[3]/div[2]");
	
	
	public CILoginModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public CILoginModal login(User user){
		switch(user.getLoginType()){
			case GOOGLE:
				clickGoogleLogin(user);
				break;
			case MASTER:
				clickCapstoneLogin();
				user.customWait().until(ExpectedConditions.elementToBeClickable(getModalElement(username)));
				getModalElement(username).sendKeys(user.getUsername());
				getModalElement(password).sendKeys(user.getPassword());
				user.customWait().until(ExpectedConditions.textToBePresentInElementValue(getModalElement(password), user.getPassword()));
				
				if(user.isHeadless()){
					getModalElement(password).sendKeys(Keys.ENTER);
				}
				else{
					getModalElement(loginButton).click();
				}
				
				break;
			default:
				user.customWait().until(ExpectedConditions.elementToBeClickable(getModalElement(username)));
				getModalElement(username).sendKeys(user.getUsername());
				getModalElement(password).sendKeys(user.getPassword());
				user.customWait().until(ExpectedConditions.textToBePresentInElementValue(getModalElement(password), user.getPassword()));
				
				if(user.isHeadless()){
					getModalElement(password).sendKeys(Keys.ENTER);
				}
				else{
					getModalElement(loginButton).click();
				}
				break;
		}
		return this;
	}
	
	public CILoginModal clickPGOLogin(){
		//Elements on the Login Modal are appearing as not visible for selenium.
		//I found this work around online. We need to find the last index and click that.
		
		int index = pageDriver.findElements(pgologin).size();
		pageDriver.findElements(pgologin).get(index-1).click();
		return this;
	}
	
	public CILoginModal clickCapstoneLogin(){
		int index = pageDriver.findElements(capstoneLogin).size();
		pageDriver.findElements(capstoneLogin).get(index-1).click();
		
		return this;
	}
	
	public CILoginModal clickGoogleLogin(User user){
		int index = pageDriver.findElements(googleLogin).size();
		pageDriver.findElements(googleLogin).get(index-1).click();		
		
		GooglePage google = new GooglePage(pageDriver);
		google.waitImplicitly(2);
		google.login(user);
		user.customWait().until(ExpectedConditions.urlContains("mycapstonelibrary"));
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
		
		return this;
	}
	
	public Boolean isErrorMessagePresent(){
		int index = pageDriver.findElements(errorMessage).size();
			
		Boolean present = pageDriver.findElements(errorMessage).get(index-1).isDisplayed();
		
		return present;
	}
}
