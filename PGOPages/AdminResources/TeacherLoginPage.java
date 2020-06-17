package AdminResources;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import AdminResources.TeacherHomeScreen;
import PGOPages.FooterMenu;
import SharedClasses.BasePage;
import SharedClasses.LoginModal;
import SharedClasses.ThirdPartyPage;
import UserClasses.User;

public class TeacherLoginPage extends BasePage {

	public By pebbleGoLogo = By.id("logo");
	public By loginModalButton = By.id("login-btn");
	public By expirationMessage = By.className("flash_warning");
	public String expirationText = "EULA has not been accepted.";
	
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

	public By pebbleGoNextButton = By.id("pebblegonext");
	public By basicBannerLink = By.id("basic_banner");
	public By[] initialVisibleElements = {pebbleGoLogo,animalsLink,scienceLink,
			biographiesLink,socialStudiesLink,dinosaursLink, pebbleGoNextButton};
	public FooterMenu footerMenu;
	public String environment;
	public String browser;
	public LoginModal loginModal;
	public User user;
	
	public By usernameField = By.id("username");
	public By passwordField = By.id("password");
	public By loginButton = By.id("login-submit-btn");

	public TeacherLoginPage(User u){
		user = u;
		pageDriver = u.getDriver();	
		browser = u.getBrowser();
		environment = u.getEnvironment();
		
		
		//Adding to run single class tests for debugging without using the JunitTestSuiteRunner
		if(environment == null){
			environment = "teacher";
		}
		
		try{
			pageDriver.get("https://teacher.pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}
		
    	footerMenu = new FooterMenu(pageDriver);
	}
	
	public TeacherLoginPage(User u, String env){
		user = u;
		pageDriver = u.getDriver();		
		browser = u.getBrowser();
		environment = u.getEnvironment();
		
		try{
			pageDriver.get("https://"+env+".pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}

    	footerMenu = new FooterMenu(pageDriver);
	}
	
	
	/**
	 * LoginPage constructor for creating a pageDriver with a non-default FirefoxProfile
	 * @param profile
	 */
	public TeacherLoginPage(FirefoxProfile profile){
		try{
			pageDriver.get("https://"+environment+".pebblego.com");
		} catch(Exception e){
			pageDriver.close();
		}

		footerMenu = new FooterMenu(pageDriver);
	}
	
	/**
	 * LoginPage constructor for navigating to one LoginPage from another LoginPage without creating a new WebDriver object.
	 * @param profile
	 */
	public TeacherLoginPage(WebDriver driver){
		pageDriver = driver;
		footerMenu = new FooterMenu(pageDriver);
	}
	
	
	
	/**
	 * Method logs in to PebbleGo based on type of User.
	 * @param usertype
	 * @return BasePage extended class for whichever page is needed.
	 */
	public TeacherHomeScreen Login(User user){
		pageDriver.findElement(usernameField).sendKeys(user.getUsername());
		pageDriver.findElement(passwordField).sendKeys(user.getPassword());
		user.customWait().until(ExpectedConditions.textToBePresentInElementValue(getModalElement(passwordField), user.getPassword()));
		
		if(user.isHeadless()){
			pageDriver.findElement(passwordField).sendKeys(Keys.ENTER);
		}
		else{
			pageDriver.findElement(loginButton).click();
		}
		
		return new TeacherHomeScreen(pageDriver);
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
	
	public ThirdPartyPage clickPebbleGoNextButton(){
		click(pebbleGoNextButton);
		return new ThirdPartyPage(pageDriver);
	}
	
	public TeacherLoginPage clickPebbleGoLogo(){
		click(pebbleGoLogo);
		waitImplicitly(1);
		return new TeacherLoginPage(pageDriver);
	}
	
	public LoginModal openLoginModal() throws InterruptedException{
		click(loginModalButton);
		return new LoginModal(pageDriver);
	}
	
	public LoginModal refreshLoginModal(){
		loginModal = new LoginModal(pageDriver);
		return loginModal;
	}
}
