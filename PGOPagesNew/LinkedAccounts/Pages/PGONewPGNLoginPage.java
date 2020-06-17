package LinkedAccounts.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdminResources.PGONewTeacherHomeScreen;
import SharedClasses.PGONewBasePage;
import UserClasses.LoginType;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGONewPGNLoginPage extends PGONewBasePage{

	public PGONewPGNLoginHeader header; 
	public By loginModalButton = By.id("login-button");
	public By switchToPebbleGoLink = By.xpath("//*[@id=\"pebblego\"]/img");
	public By freeTrialLink = By.xpath("//*[@id=\"call-out\"]/a");
	public By tabletImage = By.xpath("//*[@id=\"information\"]/div[2]/img");
	public By topCommunityLink = By.id("top-community");
	public By bottomCommunityLink = By.id("bottom-community");
	public PGONewPGNLoginModal loginModal;
	
	public By[] initialVisibleElements = {loginModalButton, freeTrialLink, tabletImage};
	
	public PGONewPGNLoginPage(User u){
		user = u;
		pageDriver = u.getDriver();
			
		try{
			if(u.getEnvironment().equals("reactqa")){
				pageDriver.get("https://qa.pebblegonext.com");
			}else if(u.getEnvironment().equals("reactstagomg")){
				pageDriver.get("https://staging.pebblegonext.com");
			}else if(u.getEnvironment().equals("site")){
				pageDriver.get("https://www.pebblegonext.com");
			}else{
				pageDriver.get("https://"+user.getEnvironment()+".pebblegonext.com");
			}
		} catch(Exception e){
			pageDriver.close();
		}
		
		header = new PGONewPGNLoginHeader(pageDriver);
	}
	
	public PGONewPGNLoginPage(User u, String env){
		user = u;
		pageDriver = u.getDriver();
		
		try{
			if(env.equals("reactqa")){
				pageDriver.get("https://qa.pebblegonext.com");
			}else{
				pageDriver.get("https://"+env+".pebblegonext.com");
			}
		} catch(Exception e){
			pageDriver.close();
		}
		
		pageDriver.get("https://"+env+".pebblegonext.com");
		
		header = new PGONewPGNLoginHeader(pageDriver);
	}
	
	public PGONewPGNLoginPage(WebDriver driver){	
		pageDriver = driver;
		
		header = new PGONewPGNLoginHeader(pageDriver);
	}
	/**
	 * Method logs in to PebbleGo based on type of User.
	 * @param usertype
	 * @return BasePage extended class for whichever page is needed.
	 */
	public PGONewBasePage login(User u){
		user = u;
		PGONewBasePage base = null;
		
		if(user.getLoginType().equals(LoginType.STUDENT)){
			base = new PGONewPGNStudentHomePage(user);
		}
		else if(user.getLoginType().equals(LoginType.GOOGLE)){
			base = new PGONewPGNStudentHomePage(user);
		}
		else if(user.getLoginType().equals(LoginType.MASTER)){
			base = new PGONewPGNStudentHomePage(user);
		}
		else if(user.getUserInfo().equals(UserInfo.EXPIRED)){
			base = new PGONewPGNLoginPage(pageDriver);
		}
		else{
			base = new PGONewTeacherHomeScreen(user);
		}
		loginModal = openLoginModal();
		
		user.customWait().until(ExpectedConditions.elementToBeClickable(getModalElement(loginModal.capstoneLogin))); 
		
		loginModal.login(user);
		
		if(!pageDriver.getCurrentUrl().contains("https")){
			pageDriver.get(pageDriver.getCurrentUrl().replace("http", "https"));
		}
		
		return base;
	}

	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public PGONewPGNLoginModal openLoginModal(){
		click(loginModalButton);
		this.waitImplicitly(3);
		return new PGONewPGNLoginModal(user);
	}
}
