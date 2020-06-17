package PGOPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import AdminResources.TeacherHomeScreen;
import PGOStudentResources.StudentHomePage;
import SharedClasses.BasePage;
import SharedClasses.LoginModal;
import SharedClasses.ThirdPartyPage;
import UserClasses.LoginType;
import UserClasses.User;
import UserClasses.UserInfo;

public class LoginPage extends BasePage {

	public By pebbleGoLogo = By.id("logo");
	public By loginModalButton = By.id("login-btn");
	public By expirationMessage = By.className("flash_warning");
	public String expirationText = "EULA has not been accepted.";
	
	public By username = By.id("username");
	public By password = By.id("password");
	public By goButton = By.id("go-button");
	
	public By animalsLink = By.xpath("//*[@id=\"info-animals\"]/a");
	public By animalesLink = By.xpath("//*[@id=\"info-animales\"]/a");
	public By scienceLink = By.xpath("//*[@id=\"info-earthSpace\"]/a");
	public By cienciaLink = By.xpath("//*[@id=\"info-ciencia\"]/a");
	public By biographiesLink = By.xpath("//*[@id=\"info-biographies\"]/a");
	public By biografiasLink = By.xpath("//*[@id=\"info-biografias\"]/a");
	public By socialStudiesLink = By.xpath("//*[@id=\"info-socialStudies\"]/a");
	public By estudiosSocialesLink = By.xpath("//*[@id=\"info-estudiosSociales\"]/a");
	public By dinosaursLink = By.xpath("//*[@id=\"info-dinosaurs\"]/a");
	
	public By animalsInfo = By.id("db-info-animals");
	public By animalesInfo = By.id("db-info-animales");
	public By scienceInfo = By.id("db-info-earthSpace");
	public By cienciaInfo = By.id("db-info-ciencia");
	public By biographiesInfo = By.id("db-info-biographies");
	public By biografiasInfo = By.id("db-info-biografias");
	public By socialStudiesInfo = By.id("db-info-socialStudies");
	public By estudiosSocialesInfo = By.id("db-info-estudiosSociales");
	public By dinosaursInfo = By.id("db-info-dinosaurs");
	
	public By freeTrialButton = By.linkText("FREE Trial");
	public By pebbleGoNextButton = By.id("pebblegonext");
	public By topCommunityLink = By.id("top-community");
	public By bottomCommunityLink = By.id("bottom-community");
	public By basicBannerLink = By.id("basic_banner");
	public By[] initialVisibleElements = {pebbleGoLogo,animalsLink,scienceLink,
			biographiesLink,socialStudiesLink,dinosaursLink, freeTrialButton, pebbleGoNextButton};
	
	public By[] initialVisibleElementsLogin = {username,password,goButton};
	
	public NavigationBar navBar;
	public FooterMenu footerMenu;
	public String environment;
	public String browser;
	public LoginModal loginModal;
	public User user;

	public LoginPage(User u){
		user = u;
		pageDriver = u.getDriver();	
		browser = u.getBrowser();
		environment = u.getEnvironment();
		
		
		//Adding to run single class tests for debugging without using the JunitTestSuiteRunner
		if(environment == null){
			environment = "staging";
		}
		
		try{
			pageDriver.get("https://"+environment+".pebblego.com");
			System.out.println("https://"+environment+".pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
    	navBar = new NavigationBar(pageDriver);
    	footerMenu = new FooterMenu(pageDriver);
	}
	
	public LoginPage(User u, String env){
		user = u;
		pageDriver = u.getDriver();		
		browser = u.getBrowser();
		environment = u.getEnvironment();
		
		try{
			pageDriver.get("https://"+env+".pebblego.com");
			System.out.println("https://"+env+".pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
    	navBar = new NavigationBar(pageDriver);
    	footerMenu = new FooterMenu(pageDriver);
	}
	
	
	/**
	 * LoginPage constructor for creating a pageDriver with a non-default FirefoxProfile
	 * @param profile
	 */
	public LoginPage(FirefoxProfile profile){
		try{
			pageDriver.get("https://"+environment+".pebblego.com");
			System.out.println("https://"+environment+".pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
		navBar = new NavigationBar(pageDriver);
		footerMenu = new FooterMenu(pageDriver);
	}
	
	/**
	 * LoginPage constructor for navigating to one LoginPage from another LoginPage without creating a new WebDriver object.
	 * @param profile
	 */
	public LoginPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
		footerMenu = new FooterMenu(pageDriver);
	}
	
	
	
	/**
	 * Method logs in to PebbleGo based on type of User.
	 * @param usertype
	 * @return BasePage extended class for whichever page is needed.
	 */
	public BasePage Login(User user){
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
		
	//	try {
	//		loginModal = openLoginModal();
	//	} catch (InterruptedException e) {
	//		e.printStackTrace();
	//	}
//		user.customWait().until(ExpectedConditions.elementToBeClickable(getModalElement(loginModal.capstoneLogin)));
		
	//	loginModal.login(user);
		
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(goButton));
		
		pageDriver.findElement(username).clear();
		pageDriver.findElement(username).sendKeys(user.getUsername());
		pageDriver.findElement(password).clear();
		pageDriver.findElement(password).sendKeys(user.getPassword());
		user.customWait().until(ExpectedConditions.textToBePresentInElementValue(password, user.getPassword()));

		pageDriver.findElement(goButton).click();

		return base;
	}
	
	/**
	 * Checks that the expiration message element is present on the Login Page when expired or incorrect credentials are given.
	 * @return boolean
	 */
	public boolean isExpirationMessagePresent(){
		System.out.println(pageDriver.findElement(expirationMessage).getText().toString());
		return pageDriver.findElement(expirationMessage).getText().toString().equals(expirationText);
	}
	
	/* Gets the set of initially visible elements */
	public By[] getInitialVisibleElements(){ return initialVisibleElements;	}
	
	/* Gets the set of initially visible elements */
	public By[] getInitialVisibleElementsLogin(){ return initialVisibleElementsLogin;	}
	
	/* Click LoginPage element methods */
	public ThirdPartyPage clickAnimalsLink(){
		click(animalsLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickScienceLink(){
		click(scienceLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickBiographiesLink(){
		click(biographiesLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickSocialStudiesLink(){
		click(socialStudiesLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickDinosaursLink(){
		click(dinosaursLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickAnimalesLink(){
		click(animalesLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public TrialPage clickFreeTrialButton(){
		click(freeTrialButton);
		return new TrialPage(pageDriver);
	}
	
	public ThirdPartyPage clickPebbleGoNextButton(){
		click(pebbleGoNextButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public LoginPage clickPebbleGoLogo(){
		click(pebbleGoLogo);
		waitImplicitly(1);
		return new LoginPage(pageDriver);
	}
	
	public LoginModal openLoginModal() throws InterruptedException{
		click(loginModalButton);
		return new LoginModal(pageDriver);
	}
	
	public LoginModal refreshLoginModal(){
		loginModal = new LoginModal(pageDriver);
		return loginModal;
	}
	
	public ThirdPartyPage clickTopCommunitiesLink(){
		click(topCommunityLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	public ThirdPartyPage clickBottomCommunityLink(){
		click(bottomCommunityLink);
		return new ThirdPartyPage(pageDriver);
	}
	
	
}
