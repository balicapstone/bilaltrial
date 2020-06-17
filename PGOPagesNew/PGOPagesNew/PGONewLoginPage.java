package PGOPagesNew;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPages.FooterMenu;
import PGOStudentResources.StudentHomePageNew;
import SharedClasses.PGONewBasePage;
import SharedClasses.PGONewGooglePage;
import UserClasses.LoginType;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGONewLoginPage extends PGONewBasePage {
	public By logo = By.className("header-logo-link"); // By.id("logo");
	public By signInText = By.id("sign-in-text");

	public By username = By.id("username");
	public By password = By.id("password");
	public By goButton = By.id("go-button");

	public By googleButton = By.id("google-button");

	public By loginErrorText = By.id("login-errors-text");

	public By privacyPolicyLink = By.linkText("Privacy Policy");
	public By termsOfUseLink = By.linkText("Terms of Use");
	public By copyrightLink = By.linkText("© 2019 Capstone. All Rights Reserved.");
	public By educatorResources = By.linkText("Educator Resources");

	public By[] initialVisibleElements = { logo, signInText, username, password, goButton, googleButton };

	public String environment;
	public String browser;
	public User user;
	public FooterMenu footerMenu;
	
	public PGONewLoginPage(User u) {
		user = u;
		pageDriver = u.getDriver();
		browser = u.getBrowser();
		environment = u.getEnvironment();

		// Adding to run single class tests for debugging without using the
		// JunitTestSuiteRunner
		if (environment == null) {
			environment = "qa";
		}

		try {
			pageDriver.get("https://" + environment + ".pebblego.com");
		} catch (Exception e) {
			pageDriver.close();
		}
	   	footerMenu = new FooterMenu(pageDriver);
	}

	public PGONewLoginPage(User u, String env) {
		user = u;
		pageDriver = u.getDriver();
		browser = u.getBrowser();
		environment = u.getEnvironment();

		try {
			pageDriver.get("https://" + env + ".pebblego.com");
		} catch (Exception e) {
			pageDriver.close();
		}
		footerMenu = new FooterMenu(pageDriver);
	}

	/**
	 * LoginPage constructor for creating a pageDriver with a non-default
	 * FirefoxProfile
	 * 
	 * @param profile
	 */
	public PGONewLoginPage(FirefoxProfile profile) {
		try {
			pageDriver.get("https://" + environment + ".pebblego.com");
		} catch (Exception e) {
			pageDriver.close();
		}
		footerMenu = new FooterMenu(pageDriver);
	}

	/**
	 * LoginPage constructor for navigating to one LoginPage from another LoginPage
	 * without creating a new WebDriver object.
	 * 
	 * @param profile
	 */
	public PGONewLoginPage(WebDriver driver) {
		pageDriver = driver;
	}

	/**
	 * Method logs in to PebbleGo based on type of User.
	 * 
	 * @param usertype
	 * @return BasePage extended class for whichever page is needed.
	 */
	public PGONewBasePage Login(User user) {
		PGONewBasePage base = null;

		if (user.getLoginType().equals(LoginType.STUDENT)) {
			base = new StudentHomePageNew(user);
		} else if (user.getLoginType().equals(LoginType.GOOGLE)) {
			base = new StudentHomePageNew(user);
		} else if (user.getLoginType().equals(LoginType.MASTER)) {
			base = new StudentHomePageNew(user);
		} else if (user.getUserInfo().equals(UserInfo.EXPIRED)) {
			base = new PGONewLoginPage(pageDriver);
		}
		/*
		 * else{ base = new TeacherHomeScreen(user); }
		 */
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(goButton));

		pageDriver.findElement(username).clear();
		pageDriver.findElement(username).sendKeys(user.getUsername());
		pageDriver.findElement(password).clear();
		pageDriver.findElement(password).sendKeys(user.getPassword());
		user.customWait().until(ExpectedConditions.textToBePresentInElementValue(password, user.getPassword()));

		pageDriver.findElement(goButton).click();

		return base;
	}

	public PGONewLoginPage clickLogo() {
		click(logo);
		waitImplicitly(1);
		return new PGONewLoginPage(pageDriver);
	}

	public PGONewGooglePage clickGoogleLogin() {
		click(googleButton);
		return new PGONewGooglePage(user);
	}
	
	/* Gets the set of initially visible elements */
	public By[] getInitialVisibleElements(){ return initialVisibleElements;	}
}