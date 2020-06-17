package LinkedAccounts.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SharedClasses.PGONewBasePage;
import SharedClasses.PGONewGooglePage;
import UserClasses.User;

public class PGONewPGNLoginModal extends PGONewBasePage{
	public By pgologin = By.xpath("//*[@id=\"select-product\"]/div[1]");
	public By capstoneLogin = By.xpath("//*[@id=\"select-product\"]/div[2]");
	public By googleLogin = By.xpath("//*[@id=\"select-product\"]/a");
	public By username = By.id("username"); //"//*[@id=\"login\"]/div[3]/form/div[1]/input");
	public By password = By.id("password");//.xpath("//*[@id=\"login\"]/div[3]/form/div[2]/input");
	public By loginButton = By.xpath("//*[contains(@class, 'rounded-submit')]");
	public By errorMessage = By.xpath("//*[contains(@class, 'modal-error')]");
	
	
	public PGONewPGNLoginModal(User u){
		pageDriver = u.getDriver();
		user = u;
	}
	
	public PGONewPGNLoginModal login(User user){
		switch(user.getLoginType()){
			case GOOGLE:
				clickGoogleLogin(user);
				break;
			case MASTER:
				clickCapstoneLogin();

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
				getModalElement(username).sendKeys(user.getUsername());
				getModalElement(password).sendKeys(user.getPassword());
				user.customWait().until(ExpectedConditions.textToBePresentInElementValue(getModalElement(password), user.getPassword()));
				//user.customWait().until(ExpectedConditions.textToBe(password, user.getPassword()));
				//waitImplicitly(2);
				
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
	
	public PGONewPGNLoginModal clickPGOLogin(){
		//Elements on the Login Modal are appearing as not visible for selenium.
		//I found this work around online. We need to find the last index and click that.
		
		int index = pageDriver.findElements(pgologin).size();
		pageDriver.findElements(pgologin).get(index-1).click();
		return this;
	}
	
	public PGONewPGNLoginModal clickCapstoneLogin(){
		int index = pageDriver.findElements(capstoneLogin).size();
		pageDriver.findElements(capstoneLogin).get(index-1).click();
		
		return this;
	}
	
	public PGONewGooglePage clickGoogleLogin(User user){
		int index = pageDriver.findElements(googleLogin).size();
		pageDriver.findElements(googleLogin).get(index-1).click();		
	
		return new PGONewGooglePage(user);
	}
	
	public Boolean isErrorMessagePresent(){
		int index = pageDriver.findElements(errorMessage).size();
			
		Boolean present = pageDriver.findElements(errorMessage).get(index-1).isDisplayed();
		
		return present;
	}
}
