package CITests.CIPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BuildingAdmin.BuildingAdminDashboard;
import LearnMorePages.EducatorsPage;
import LearnMorePages.LearnMoreAboutPage;
import LearnMorePages.PrivacyPolicyPage;
import LearnMorePages.TermsOfUsePage;
import SharedClasses.BasePage;
import SuperAdmin.SuperAdminLoginPage;
import UserClasses.LoginType;
import UserClasses.User;

public class CILoginPage extends BasePage{
	private By loginModalButton = By.id("login-btn");
	private By learnMoreButton = By.id("brnLearn");
	public By aboutLink = By.linkText("About Capstone");
	public By privacyPolicyLink = By.linkText("Privacy Policy");
	public By termsOfUseLink = By.linkText("Terms of Use");
	public By licenseAgreementLink = By.linkText("License Agreement");
	public By contactUsLink = By.linkText("Contact Us");
	public By educators = By.linkText("Educators");
	public By capstoneLogo = By.id("capstone-logo");
	public By EulaAlert = By.id("eulaAlert");
	private By revisionText = By.id("revision");
	private By[] initialVisibleElements = {loginModalButton, learnMoreButton, aboutLink, 
			privacyPolicyLink, termsOfUseLink, licenseAgreementLink, 
			contactUsLink, capstoneLogo, revisionText};
	public StudentEulaPopUp studentEula;
	public AdminEulaPopUp adminEula;
	public CILoginModal loginModal;
	public String environment;
	public String browser;
	private String site = "mycapstonelibrary.com";
	public WebDriverWait wait;

	
	public CILoginPage(User u){
		pageDriver = u.getDriver();
		environment = u.getEnvironment();
		browser = u.getBrowser();
		
		//Adding to run single class tests for debugging without using the JunitTestSuiteRunner
		if(environment == null){
				environment = "www";
		}
		
		wait = new WebDriverWait(pageDriver, 15);
		pageDriver.get("https://"+environment+"."+site);
		
		studentEula = new StudentEulaPopUp(pageDriver);
		adminEula = new AdminEulaPopUp(pageDriver);
	}
	
	public CILoginPage(User u, String env){
		pageDriver = u.getDriver();
		environment = u.getEnvironment();
		browser = u.getBrowser();
		//Adding to run single class tests for debugging without using the JunitTestSuiteRunner	
		
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		
		pageDriver.get("https://"+env+"."+site);
		
		wait = new WebDriverWait(pageDriver, 15);
		studentEula = new StudentEulaPopUp(pageDriver);
		adminEula = new AdminEulaPopUp(pageDriver);	
	}
	
	public void refreshEULAModals(){
		studentEula = new StudentEulaPopUp(pageDriver);
		adminEula = new AdminEulaPopUp(pageDriver);
	}
	
	public CILoginPage(WebDriver driver){
		pageDriver = driver;
		wait = new WebDriverWait(pageDriver, 15);
	}
	
	public By getloginButton(){
		return loginModalButton;
	}
	
	public BasePage login(User user){
		environment = user.getEnvironment();
		
		if(environment == null){
			environment = "staging";
		}

		BasePage base = null;
		
		if(user.getLoginType().equals(LoginType.STUDENT)){
			base = new CITests.CIPages.StudentHomePage(pageDriver);
		}
		else if(user.getLoginType().equals(LoginType.GOOGLE)){
			base = new CITests.CIPages.StudentHomePage(pageDriver);
		}
		else if(user.getLoginType().equals(LoginType.MASTER)){
			base = new CITests.CIPages.StudentHomePage(pageDriver);
		}
		else if(user.getLoginType().equals(LoginType.SUPERADMIN)){
			base = new SuperAdminLoginPage(user);
		}
		else{
			base = new BuildingAdminDashboard(pageDriver);
		}
			
		CILoginModal loginModal = openLoginModal();
		user.customWait().until(ExpectedConditions.elementToBeClickable(loginModal.getModalElement(loginModal.googleLogin)));
		loginModal.login(user);
		return base;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public LearnMoreAboutPage clickLearnMoreButton(){
		scrollToElement(pageDriver.findElement(learnMoreButton));
		click(learnMoreButton);
		return new LearnMoreAboutPage(pageDriver);
	}
	
	public PrivacyPolicyPage clickPrivacyPolicyLink(){
		scrollToElement(pageDriver.findElement(privacyPolicyLink));
		click(privacyPolicyLink);
		return new PrivacyPolicyPage(pageDriver);
	}
	
	public TermsOfUsePage clickTermsOfUseLink(){
		scrollToElement(pageDriver.findElement(termsOfUseLink));
		click(termsOfUseLink);
		return new TermsOfUsePage(pageDriver);
	}
	
	public EducatorsPage clickEducatorsLink(){
		scrollToElement(pageDriver.findElement(educators));
		click(educators);
		return new EducatorsPage(pageDriver);
	}
	
	//Tests that the warning message is populated if a user is restricted from logging in
	public boolean isWarningPresent(){
		boolean present = false;
		try{
			present = pageDriver.findElement(By.className("flash_warning")).isDisplayed();
		} catch(Exception e){
			
		}
		return present;		
	}
	
	public CILoginModal openLoginModal(){
		click(loginModalButton);
		CILoginModal modal = new CILoginModal(pageDriver);
		wait.until(ExpectedConditions.visibilityOf(modal.getModalElement(modal.capstoneLogin)));
		return modal;
	}
}
