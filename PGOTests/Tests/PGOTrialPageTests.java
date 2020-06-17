package Tests;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PGOPages.LoginPage;
import PGOPages.TrialPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGOTrialPageTests{    
    public static LoginPage loginPage;
    public static TrialPage trial;
    public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		loginPage = new LoginPage(user);
		
		try{
			trial = loginPage.clickFreeTrialButton();
		} catch(Exception e){
			user.getWatcher().TakeScreenshot("FailedToRunExecuteBeforeForPGOTrialPageTests");
		}
	}
	
	@AfterClass
	public static void executeAfter(){
		user.getDriver().quit();
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
    		trial = loginPage.clickFreeTrialButton();
    	}
    };
	
	@Rule
	public Retry retry = new Retry(3);
    
    
    /**
     * Tests that all error messages will display when the new user submit button is pressed.
     */
	@Test
	@Category(SmokeTests.class)
    public void testErrosAppearForBlankFormSubmission(){
    	trial.clickSubmitButton();
    	trial.waitImplicitly(1);
    	
    	assertTrue("Asserts that the confirmation message does not appear when no info is entered and submitted",
    			!trial.isMessagePresent());
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that the administrator user name is already registered and the correct error message is displayed.
     */
	@Test
    public void testAdminAlreadyRegistered(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
    	trial.sendKeysClean("garageteacher", trial.getAdministratorUsername());
    	trial.sendKeysClean("test", trial.getAdministratorPassword());
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	trial.waitImplicitly(5);
    	
    	assertTrue("Asserts that an administrator user name is already registered and the correct error message is displayed",
    			trial.getElementText(trial.getFlashNotice()).equals("Oops! You’ve had this demo and/or you own all available databases. Please contact customer service if this is an error."));
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }

    /**
     * Tests that an invalid user name or password is entered in the Administrator information and the correct error message is displayed.
     */
	@Test
	@Category(SmokeTests.class)
    public void testInvalidExistingUser(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
    	trial.sendKeysClean("asdfasdf", trial.getAdministratorUsername());
    	trial.sendKeysClean("test", trial.getAdministratorPassword());
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that an invalid username or password gets the correct error message",
    			trial.getElementText(trial.getFlashNotice()).equals("Username and password could be wrong, or the subscription is inactive."));
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	loginPage.refresh();
    	trial = loginPage.clickFreeTrialButton();
    }

    /**
     * Tests that an invalid password is entered in the Administrator password field and the correct error message is displayed.
     */
	@Test
    public void testInvalidExistingUserPassword(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
    	trial.sendKeysClean("garageteacher", trial.getAdministratorUsername());
    	trial.sendKeysClean("asdf", trial.getAdministratorPassword());
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	assertTrue("Tests that an invalid password and valid administrator username gives the correct error message",
    			trial.getElementText(trial.getFlashNotice()).equals("Username and password could be wrong, or the subscription is inactive."));
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	loginPage.refresh();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that a record already exists and that the appropriate error message appears.
     */
	@Test
	@Category(SmokeTests.class)
    public void testRecordAlreadyExists(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.clickNewUserRadio();
    	trial.fillOutTrialInformation("TestTest@test.com");
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that a trial already exists and that the correct error message appears",
    			trial.getElementText(trial.getFlashNotice()).equals("You’ve had this demo and/or you own all available databases. Please contact customer service if this is an error."));   	
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that an error pops up after an incorrect email is supplied.
     */
	@Test
    public void testEmailAddressError(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.clickNewUserRadio();
		trial.fillOutTrialInformation("test@com");
    	//trial.sendKeysToElement("Test@.com", trial.getEmailField());
  
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that an the correct error appears after an incorrect email is supplied",
    			trial.getElementText(trial.getEmailFieldError()).equals("The email address is invalid!"));
    	
    	trial.scrollToTopofPage();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.navBar.PebbleGoLogo));
    	
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that an email error pops up when an incorrect email is entered.
     */
	@Test
    public void testIncorrectEmail(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.clickNewUserRadio();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.firstNameField));
		
    	trial.fillOutTrialInformation();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.getStateDropdown().selectByValue("MN");
    	
    	trial.getDriver().findElement(trial.getEmailField()).clear();
    	user.customWait().until(ExpectedConditions.textToBePresentInElementValue(trial.getEmailField(), ""));
    	trial.sendKeysToElement("TestTest", trial.getEmailField());

    	user.customWait().until(ExpectedConditions.textToBePresentInElementValue(trial.getEmailField(), "TestTest"));
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that the correct error appears when an incorrect email is supplied",
    			!trial.isMessagePresent()); 
    	
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that an zip code error pops up when an incorrect zip code is entered.
     */
	@Test
    public void testIncorrectZipCode(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.clickNewUserRadio();
		trial.fillOutTrialInformation();
		trial.clearField(trial.getZipField());
    	trial.sendKeysToElement("1", trial.getZipField());
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that the user cannot proceed with a trial request without a valid zipcode",
    			!trial.getElementText(trial.getFlashNotice()).equals("Thank you! You will receive an email confirmation shortly."));
    	
    	trial.scrollToTopofPage();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.navBar.PebbleGoLogo));
    	
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
   
	
    /**
     * Tests that a user can sign up for a free trial and that the successful submission message pops up.
     */
	@Test
	@Category(SmokeTests.class)
    public void testSignUpForTrialSuccessMessage(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.clickNewUserRadio();
    	trial.waitImplicitly(1);
    	trial.fillOutTrialInformation();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.getStateDropdown().selectByValue("MN");
    	
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that a user can sign up for a free trial and that the successful submission message pops up",
    			trial.getElementText(trial.getFlashNotice()).equals("Thank you! You will receive an email confirmation shortly."));
    	loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();
    }  
	
	@Test
	@Category(SmokeTests.class)
	public void testStateDropdownAppearsAndDisappears(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		assertTrue(!trial.isStateDropdownVisible());
		
		trial.getCountryDropdown().selectByValue("US");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("state")));
		
		assertTrue("Asserts that the State dropdown is visible when the US is selected from the country dropdown",
				trial.isStateDropdownVisible());
		
		trial.getCountryDropdown().selectByValue("AF");
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(By.id("state")));
		
		assertTrue("Asserts that the State dropdown is not visible when a country other than the US is selected from the country dropdown",
				!trial.isStateDropdownVisible());
		loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();		
	}
	
	@Test
	public void testTrialMustSelectUsertype(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.fillOutTrialInformation().clickOverEighteenCheckbox().clickSubmitButton();
		
		assertTrue("Asserts that the User cannot submit a trial without choosing a user type", !trial.isMessagePresent());
		
		trial.scrollToTopofPage();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.navBar.PebbleGoLogo));
		
		loginPage = trial.navBar.clickPebbleGoLogo();
    	trial = loginPage.clickFreeTrialButton();	
	}
	
	@Test
	public void testUsernamePasswordDissappearForNewUser(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
		
		assertTrue("Asserts that Username and Password fields appear when the existing user radio is clicked",
				trial.isElementPresent(trial.getAdministratorUsername()) && trial.isElementPresent(trial.getAdministratorPassword()));
		
		trial.clickNewUserRadio();
		
		assertTrue("Asserts that Username and Password fields dissaappear when the new user radio is clicked",
				!trial.isElementPresent(trial.getAdministratorUsername()) && !trial.isElementPresent(trial.getAdministratorPassword()));
	}
	 @Rule
	    public TestRule watcher = new TestWatcher() {
	       protected void starting(Description description) {
	          System.out.println("Starting test: " + description.getMethodName());
	        
	          
	       }
	    };
	
}
