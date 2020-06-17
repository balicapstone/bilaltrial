package SuperAdmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import BuildingAdmin.BuildingAdminDashboard;
import SharedClasses.BasePage;
import TrueUserTests.TrueUser.RegressionSuiteRunner;
import UserClasses.User;

public class SuperAdminLoginPage extends BasePage{
	public By signInButton = By.className("bttn-main");
	public By usernameField = By.name("username");
	public By passwordField = By.name("password");
	public String environment = RegressionSuiteRunner.environment;
	public String browser = RegressionSuiteRunner.browser;
	
	public SuperAdminLoginPage(User user){
		pageDriver = user.getDriver();

		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		//Added to run tests without using JunitTestSuiteRunner
		if(user.getEnvironment() == null){
			environment = "www";
		}
		
		try{
			pageDriver.get("http://"+user.getEnvironment()+".mycapstonelibrary.com/superadmin");
		} catch(Exception e){
			pageDriver.close();
		}
		
	}
	
	public SuperAdminHomePage login(User user){				
		sendKeysToElement(user.getUsername(), usernameField);
		sendKeysToElement(user.getPassword(), passwordField);
		click(signInButton);
		return new SuperAdminHomePage(pageDriver);
	}
	
	public BuildingAdminDashboard loginForBuildingAdmin(User user){
		sendKeysToElement(user.getUsername(), usernameField);
		sendKeysToElement(user.getPassword(), passwordField);
		click(signInButton);

		return new BuildingAdminDashboard(pageDriver);
	}
}
