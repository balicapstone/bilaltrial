package Tests;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGNPages.LoginPage;
import PGNPages.TrialPage;
import TrueUserTests.TrueUser.PGNSmokeTest;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;
import UserClasses.UserInfo;

public class PGNTrialPageTests{    
    static LoginPage loginPage;
    static TrialPage trial;
    static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		loginPage = new LoginPage(user);
		trial = loginPage.clickFreeTrialButton();
		trial.waitImplicitly(3);
	}
	
	@AfterClass
	public static void executeAfter(){
		user.quit();
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
    		trial.waitImplicitly(3);
    	}
    };
	
	@Rule
	public Retry retry = new Retry(3);
    
    /**
     * Tests that all error messages will display when the new user submit button is pressed.
     */
	@Test
	@Category(PGNSmokeTest.class)
    public void testAllErrorsAppear(){
		trial.clickExistingUserRadio();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.clickSubmitButton();
    	trial.waitImplicitly(1);
    	
    	assertTrue("Asserts that an the correct error appears after an incorrect email is supplied",
    			!trial.isMessagePresent());//!trial.getElementText(trial.getFlashNotice()).equals("We will review your request for a free trial within the next business day. Once setup is complete, you will receive an email with log-in information."));
    	
    	loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that the administrator user name is already registered and the correct error message is displayed.
     */
	@Test
    public void testAdminAlreadyRegistered(){
		trial.clickExistingUserRadio();
    	trial.sendKeysToElement("trexteacher",trial.getAdministratorUsername());
    	trial.sendKeysToElement("sm4ll4rms", trial.getAdministratorPassword());
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	trial.waitImplicitly(1);
    	
    	assertTrue("Asserts that an administrator user name is already registered and the correct error message is displayed",
    			trial.getElementText(trial.getFlashNotice()).equals("Oops! You’ve had this demo and/or you own all available databases. Please contact customer service if this is an error."));
    	loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();
    }

    /**
     * Tests that an invalid user name or password is entered in the Administrator information and the correct error message is displayed.
     */
	@Test
	@Category(PGNSmokeTest.class)
    public void testInvalidExistingUser(){
		trial.clickExistingUserRadio();
    	trial.sendKeysToElement("asdfasdf", trial.getAdministratorUsername());
    	trial.sendKeysToElement("test", trial.getAdministratorPassword());
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that an invalid username or password gets the correct error message",
    			trial.getElementText(trial.getFlashNotice()).equals("Username and password could be wrong, or the subscription is inactive."));
    	loginPage = trial.header.clickLogo();
    	//This is needed because of a bug that opens the log in modal when logging in with bad credentials
    	loginPage.refresh();
    	trial = loginPage.clickFreeTrialButton();
    }

    /**
     * Tests that an invalid password is entered in the Administrator password field and the correct error message is displayed.
     */
	@Test
    public void testInvalidExistingUserPassword(){
		trial.clickExistingUserRadio();
    	trial.sendKeysToElement("garageteacher", trial.getAdministratorUsername());
    	trial.sendKeysToElement("asdf", trial.getAdministratorPassword());
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	assertTrue("Tests that an invalid password and valid administrator username gives the correct error message",
    			trial.getElementText(trial.getFlashNotice()).equals("Username and password could be wrong, or the subscription is inactive."));
    	loginPage = trial.header.clickLogo();
    	//This is needed because of a bug that opens the log in modal when logging in with bad credentials
    	loginPage.refresh();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that a record already exists and that the appropriate error message appears.
     */
	@Test
	@Category(PGNSmokeTest.class)
    public void testRecordAlreadyExists(){
		trial.clickNewUserRadio();
    	trial.fillOutTrialInformation("TestTest@test.com");
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that a trial already exists and that the correct error message appears",
    			trial.getElementText(trial.getFlashNotice()).equals("You’ve had this demo and/or you own all available databases. Please contact customer service if this is an error."));   	
    	loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that an error pops up after an incorrect email is supplied.
     */
	@Test
    public void testEmailAddressError(){
		trial.clickNewUserRadio();
    	trial.sendKeysToElement("Test@.com", trial.getEmailField());
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that an the correct error appears after an incorrect email is supplied",
    			!trial.getElementText(trial.getEmailError()).equals("The email address is invalid!"));
    	loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that an email error pops up when an incorrect email is entered.
     */
	@Test
	@Category(PGNSmokeTest.class)
    public void testIncorrectEmail(){
		trial.clickNewUserRadio();
    	trial.fillOutTrialInformation();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.getStateDropdown().selectByValue("MN");
    	
    	trial.getDriver().findElement(trial.getEmailField()).clear();
    	trial.waitImplicitly(1);
    	trial.getDriver().findElement(trial.getEmailField()).sendKeys("TestTest");
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that the correct error appears when an incorrect email is supplied",
    			!trial.getElementText(trial.getEmailError()).equals("The email address is invalid!"));
    	loginPage = trial.header.clickLogo();
    	loginPage.refresh();
    	trial = loginPage.clickFreeTrialButton();
    }
    
    /**
     * Tests that an zip code error pops up when an incorrect zip code is entered.
     */
	@Test
    public void testIncorrectZipCode(){
		trial.clickNewUserRadio();
		trial.fillOutTrialInformation();
		trial.clearField(trial.getZipField());
    	trial.sendKeysToElement("", trial.getZipField());
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that the user cannot advance without a valid zip code",
    			!trial.getElementText(trial.getFlashNotice()).equals("Thank you! You will receive an email confirmation shortly."));
    	loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();
    }
    

    /**
     * Tests that a user can sign up for a free trial and that the successful submission message pops up.
     */
	@Test
	@Category(PGNSmokeTest.class)
    public void testSignUpForTrialSuccessMessage(){
		trial.clickNewUserRadio();
    	trial.waitImplicitly(1);
    	trial.fillOutTrialInformation();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.getStateDropdown().selectByValue("MN");
    	
    	trial.clickSubmitButton();
    	trial.waitImplicitly(3);
    	
    	assertTrue("Asserts that a user can sign up for a free trial and that the successful submission message pops up",
    			trial.getElementText(trial.getFlashNotice()).equals("Thank you! You will receive an email confirmation shortly."));
    	loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();
    }  
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testStateDropdownAppearsAndDisappears(){
		assertTrue(!trial.isStateDropdownVisible());
		
		trial.getCountryDropdown().selectByValue("US");
		trial.waitImplicitly(1);
		
		assertTrue("Asserts that the State dropdown is visible when the US is selected from the country dropdown",
				trial.isStateDropdownVisible());
		
		trial.getCountryDropdown().selectByValue("AF");
		trial.waitImplicitly(1);
		
		assertTrue("Asserts that the State dropdown is not visible when a country other than the US is selected from the country dropdown",
				!trial.isStateDropdownVisible());
		loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();		
	}
	
	@Test
	@Category(PGNSmokeTest.class)
	public void testTrialMustSelectUsertype(){
		trial.fillOutTrialInformation().clickOverEighteenCheckbox().clickSubmitButton();
		
		assertTrue("Asserts that the User cannot submit a trial without choosing a user type", !trial.isMessagePresent());
		loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();	
	}
	
	@Test
	public void testUsernamePasswordDissappearForNewUser(){
		trial.clickExistingUserRadio();
		
		assertTrue("Asserts that Username and Password fields appear when the existing user radio is clicked",
				trial.isElementPresent(trial.getAdministratorUsername()) && trial.isElementPresent(trial.getAdministratorPassword()));
		
		trial.clickNewUserRadio();
		
		assertTrue("Asserts that Username and Password fields dissaappear when the new user radio is clicked",
				!trial.isElementPresent(trial.getAdministratorUsername()) && !trial.isElementPresent(trial.getAdministratorPassword()));
		loginPage = trial.header.clickLogo();
    	trial = loginPage.clickFreeTrialButton();	
	}
}
