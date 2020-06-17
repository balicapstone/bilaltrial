package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import AdminResources.TeacherHomeScreen;
import PGNStudentResources.StudentHomePage;
import SharedClasses.BasePage;
import SharedClasses.LoginModal;
import SharedClasses.ThirdPartyPage;
import UserClasses.LoginType;
import UserClasses.User;
import UserClasses.UserInfo;

public class LoginPage extends BasePage{

	public LoginHeader header; 
	public FooterMenu footer;
	public By loginModalButton = By.id("login-button");
	public By switchToPebbleGoLink = By.xpath("//*[@id=\"pebblego\"]/img");
	public By freeTrialLink = By.xpath("//*[@id=\"call-out\"]/a");
	public By tabletImage = By.xpath("//*[@id=\"information\"]/div[2]/img");
	public By topCommunityLink = By.id("top-community");
	public By bottomCommunityLink = By.id("bottom-community");
	public LoginModal loginModal;
	public String environment;
	public String browser;
	
	public By[] initialVisibleElements = {loginModalButton, freeTrialLink, tabletImage};
	
	public LoginPage(User u){
		pageDriver = u.getDriver();
		environment = u.getEnvironment();
		browser = u.getBrowser();
		
		
		if(environment == null){
				environment = "www";
		}
			
		try{
			pageDriver.get("https://"+environment+".pebblegonext.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
		header = new LoginHeader(pageDriver);
		footer = new FooterMenu(pageDriver);
	}
	
	public LoginPage(User u, String env){
		pageDriver = u.getDriver();
		environment = u.getEnvironment();
		browser = u.getBrowser();
		
		try{
			pageDriver.get("https://"+env+".pebblegonext.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
		pageDriver.get("https://"+env+".pebblegonext.com");
		
		header = new LoginHeader(pageDriver);
		footer = new FooterMenu(pageDriver);
	}
	
	public LoginPage(WebDriver driver){
		//Adding to run single class tests for debugging without using the JunitTestSuiteRunner
		if(environment == null){
				environment = "www";
		}
			
		pageDriver = driver;
		
		header = new LoginHeader(pageDriver);
		footer = new FooterMenu(pageDriver);
	}
	/**
	 * Method logs in to PebbleGo based on type of User.
	 * @param usertype
	 * @return BasePage extended class for whichever page is needed.
	 */
	public BasePage login(User user){
		BasePage base = null;
		
		if(user.getLoginType().equals(LoginType.STUDENT)){
			base = new StudentHomePage(pageDriver);
		}
		else if(user.getLoginType().equals(LoginType.GOOGLE)){
			base = new StudentHomePage(pageDriver);
		}
		else if(user.getLoginType().equals(LoginType.MASTER)){
			base = new StudentHomePage(pageDriver);
		}
		else if(user.getUserInfo().equals(UserInfo.EXPIRED)){
			base = new LoginPage(pageDriver);
		}
		else{
			base = new TeacherHomeScreen(pageDriver);
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
	
	public TrialPage clickFreeTrialButton(){
		click(freeTrialLink);
		return new TrialPage(pageDriver);
	}
	
	public ThirdPartyPage switchToPebbleGo(){
		click(switchToPebbleGoLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public LoginModal openLoginModal(){
		click(loginModalButton);
		this.waitImplicitly(3);
		return new LoginModal(pageDriver);
	}
	
	public ThirdPartyPage clickTopCommunitiesLink(){
		click(topCommunityLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickBottomCommunitiesLink(){
		click(bottomCommunityLink);
		return new ThirdPartyPage(pageDriver);
	}
}
