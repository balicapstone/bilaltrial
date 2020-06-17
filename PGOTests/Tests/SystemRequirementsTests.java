package Tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGOPages.LoginPage;
import PGOPages.SystemRequirementsPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

import static org.junit.Assert.*;


public class SystemRequirementsTests{
	static LoginPage loginPage;
	static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
	
		loginPage = new LoginPage(user);

	}
	
	@AfterClass
	public static void executeAfter(){
		loginPage.quit();
	}
	
	
	@Rule
	public TestWatcher restart = new TestWatcher(){
		@Override
		public void failed(Throwable e, Description description){
	   		try{
	   			user.getWatcher().TakeScreenshot(description.getDisplayName());
	   		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
			
			user.quit();
			user.makeNewDriver();
			loginPage = new LoginPage(user);
		}
	};
	
	@Rule
	public Retry retry = new Retry(3);
	
	/**
	 * Tests that the correct version of browser is displayed.
	 */
	@Test
	@Category(SmokeTests.class)
    public void testSystemRequirementsPageVersion(){
    	SystemRequirementsPage systemRequirements = loginPage.navBar.clickSystemRequirementsButton();
    	systemRequirements.clickCheckYourSystem();
    	
    	assertTrue("Asserts that the System Requirements page gives the correct browser version when prompted",
    			systemRequirements.getPageVersionElement().equals(systemRequirements.getDriverBrowserVersion()));
    	loginPage = systemRequirements.navBar.clickPebbleGoLogo(); 
    }
    
    /**
     * Tests that the Platform is displayed and correct. 
     */
	@Test
	@Category(SmokeTests.class)
    public void testSystemRequirementsPagePlatform(){
    	SystemRequirementsPage systemRequirements = loginPage.navBar.clickSystemRequirementsButton();
    	systemRequirements.clickCheckYourSystem();
    	
      	assertTrue("Asserts that the System Requirements page gives the correct platform is given when prompted",
      			systemRequirements.getPagePlatformElement().toString().toLowerCase().contains(systemRequirements.getDriverPlatform().toLowerCase()));
      	loginPage = systemRequirements.navBar.clickPebbleGoLogo();
    }
	 @Rule
	    public TestRule watcher = new TestWatcher() {
	       protected void starting(Description description) {
	          System.out.println("Starting test: " + description.getMethodName());
	        
	          
	       }
	    };
}
