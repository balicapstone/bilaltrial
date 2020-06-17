package LinkedAccounts.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CITests.CIPages.AdminEulaPopUp;
import CITests.CIPages.StudentEulaPopUp;
import SharedClasses.PGONewBasePage;
import UserClasses.LoginType;
import UserClasses.User;

public class PGONewCILoginPage extends PGONewBasePage{
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
	public PGONewCILoginModal loginModal;
	private String site = "mycapstonelibrary.com";
	public WebDriverWait wait;

	
	public PGONewCILoginPage(User u){
		pageDriver = u.getDriver();
		
		if(u.getEnvironment().equals("reactqa")){
			pageDriver.get("https://qa."+site);
		}else if(u.getEnvironment().equals("reactstaging")){
			pageDriver.get("https://staging." + site);
		}else if(u.getEnvironment().equals("site")){
			pageDriver.get("https://www." + site);
		} else{
			pageDriver.get("https://"+user.getEnvironment()+"." + site);
		}
		
		studentEula = new StudentEulaPopUp(pageDriver);
		adminEula = new AdminEulaPopUp(pageDriver);
	}
	
	public PGONewCILoginPage(User u, String env){
		pageDriver = u.getDriver();

		if(u.getEnvironment().equals("reactqa")){
			pageDriver.get("https://qa."+site);
		}else{
			pageDriver.get("https://"+env+"." + site);
		}

		studentEula = new StudentEulaPopUp(pageDriver);
		adminEula = new AdminEulaPopUp(pageDriver);	
	}
	
	public void refreshEULAModals(){
		studentEula = new StudentEulaPopUp(pageDriver);
		adminEula = new AdminEulaPopUp(pageDriver);
	}
	
	public PGONewCILoginPage(WebDriver driver){
		pageDriver = driver;
		wait = new WebDriverWait(pageDriver, 15);
	}
	
	public By getloginButton(){
		return loginModalButton;
	}
	
	public PGONewBasePage login(User user){
		PGONewBasePage base = null;
		
		if(user.getLoginType().equals(LoginType.STUDENT)){
			base = new PGONewCIStudentHomePage(user);
		}
		else if(user.getLoginType().equals(LoginType.GOOGLE)){
			base = new PGONewCIStudentHomePage(user);
		}
		else if(user.getLoginType().equals(LoginType.MASTER)){
			base = new PGONewCIStudentHomePage(user);
		}
		else{
			base = new PGONewBuildingAdminDashboard(user);
		}
			
		PGONewCILoginModal loginModal = openLoginModal();
		user.customWait().until(ExpectedConditions.elementToBeClickable(loginModal.getModalElement(loginModal.googleLogin)));
		loginModal.login(user);
		return base;
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
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
	
	public PGONewCILoginModal openLoginModal(){
		click(loginModalButton);
		PGONewCILoginModal modal = new PGONewCILoginModal(pageDriver);
		return modal;
	}
}
