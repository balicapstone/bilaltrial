package Tests;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PGOPagesNew.PGONewLoginPage;
import PGOPagesNew.PGONewTrialPage;
import TrueUserTests.TrueUser.Retry;
import TrueUserTests.TrueUser.SmokeTests;
import UserClasses.User;
import UserClasses.UserInfo;

public class RegressionPGONewTrialPageTests{    
    public static PGONewLoginPage loginPage;
    public static PGONewTrialPage trial;
    public static User user;
	
	@BeforeClass
	public static void executeBefore(){
		user = new User(UserInfo.GARAGESTUDENT);
		
		try{
			trial = new PGONewTrialPage(user);
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
    		
    		trial = new PGONewTrialPage(user);
    	}
    };
	
    
	@Rule
	public Retry retry = new Retry(3);
	
	
	@Test
	@Category(SmokeTests.class)
	public void testLicenseAgreementLink(){
    	trial.verifyLinkContainsURLByElement(trial.licenseAgreementLink, "https://pgassets-qa.pebblego.com/content/Eula-1.0.0.pdf");
	}
    
    /**
     * Tests that all error messages will display when the new user submit button is pressed.
     */
	@Test
	@Category(SmokeTests.class)
    public void testErrorsAppearForBlankFormSubmission(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    	
    	trial.clickSubmitButton();
    	trial.waitImplicitly(1);
    	
    	assertTrue("Asserts that the confirmation message does not appear when no info is entered and submitted",
    			trial.getDriver().findElement(trial.emailField).getAttribute("validationMessage").equals("Please fill out this field."));
    
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }
    
    /**
     * Tests that the administrator user name is already registered and the correct error message is displayed.
     */
	@Test
    public void testAdminAlreadyRegistered(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
    	trial.sendKeysToElement("trexteacher", trial.administratorUsername);
    	trial.sendKeysToElement("sm4ll4rms", trial.administratorPassword);
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("submit-message-text")));
    	user.customWait().until(ExpectedConditions.textToBePresentInElementLocated(By.id("submit-message-text"), "demo"));
    	
    	assertTrue("Asserts that an administrator user name is already registered and the correct error message is displayed",
    			trial.getElementText(trial.flashNotice).equals("Oops! You’ve had this demo and/or you own all available databases. Please contact customer service if this is an error."));
    	
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
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
    	trial.sendKeysToElement("asdfasdf", trial.administratorUsername);
    	trial.sendKeysToElement("test", trial.administratorPassword);
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("submit-message-text")));
    	user.customWait().until(ExpectedConditions.textToBePresentInElementLocated(By.id("submit-message-text"), "subscription"));
    	
    	assertTrue("Asserts that an invalid username or password gets the correct error message",
    			trial.getElementText(trial.flashNotice).equals("Username and password could be wrong, or the subscription is inactive."));

    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }

    /**
     * Tests that an invalid password is entered in the Administrator password field and the correct error message is displayed.
     */
	@Test
    public void testInvalidExistingUserPassword(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
    	trial.sendKeysToElement("garageteacher", trial.administratorUsername);
    	trial.sendKeysToElement("asdf", trial.administratorPassword);
    	trial.fillOutTrialInformation();
    	trial.clickSubmitButton();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("submit-message-text")));
    	user.customWait().until(ExpectedConditions.textToBePresentInElementLocated(By.id("submit-message-text"), "Username"));
    	
    	assertTrue("Tests that an invalid password and valid administrator username gives the correct error message",
    			trial.getElementText(trial.flashNotice).equals("Username and password could be wrong, or the subscription is inactive."));

    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }
    
    /**
     * Tests that a record already exists and that the appropriate error message appears.
     */
	@Test
	@Category(SmokeTests.class)
    public void testRecordAlreadyExists(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    	
		trial.clickNewUserRadio();
    	trial.fillOutTrialInformation("testingHAHA@test.com");
    	trial.clickSubmitButton();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("submit-message-text")));
    	user.customWait().until(ExpectedConditions.textToBePresentInElementLocated(By.id("submit-message-text"), "customer service"));
    	
    	assertTrue("Asserts that a trial already exists and that the correct error message appears",
    			trial.getElementText(trial.flashNotice).equals("You’ve had this demo and/or you own all available databases. Please contact customer service if this is an error."));   	

    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }
    
    /**
     * Tests that an error pops up after an incorrect email is supplied.
     */
	@Test
    public void testEmailAddressError(){
		trial.refresh();
		user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.newUserRadio));
		
		trial.fillOutTrialInformation("test@com");
  
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that an the correct error appears after an incorrect email is supplied",
    			trial.getDriver().findElement(By.className("input-error-text")).getText().equals("The email address is invalid!")
    			&& trial.getDriver().findElement(trial.flashNotice).getText().isEmpty());
    	
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }
    
    /**
     * Tests that an email error pops up when an incorrect email is entered.
     */
	@Test
    public void testIncorrectEmail(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
    	trial.fillOutTrialInformation();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.getStateDropdown().selectByValue("MN");
    	
    	trial.getDriver().findElement(trial.emailField).clear();
    	user.customWait().until(ExpectedConditions.textToBePresentInElementValue(trial.emailField, ""));
    	trial.sendKeysToElement("TestTest", trial.emailField);

    	user.customWait().until(ExpectedConditions.textToBePresentInElementValue(trial.emailField, "TestTest"));
    	trial.clickSubmitButton();
    	
    	assertTrue("Asserts that the correct error appears when an incorrect email is supplied",
    			trial.getDriver().findElement(trial.emailField).getAttribute("validationMessage").equals("Please include an '@' in the email address. 'TestTest' is missing an '@'.")); 
    	
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
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
		trial.clearField(trial.zipField);
    	trial.sendKeysToElement("", trial.zipField);
    	trial.clickSubmitButton();
    	trial.waitImplicitly(3);
    	
    	assertTrue("Asserts that the user cannot proceed with a trial request without a valid zipcode",
    			trial.getDriver().findElement(trial.zipField).getAttribute("validationMessage").equals("Please fill out this field.")
    			&& trial.getDriver().findElement(trial.flashNotice).getText().isEmpty());
    	
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }
   
	
    /**
     * Tests that a user can sign up for a free trial and that the successful submission message pops up.
     */
	@Test
	@Category(SmokeTests.class)
    public void testSignUpForTrialSuccessMessage(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    	
    	trial.fillOutTrialInformation();
    	trial.getCountryDropdown().selectByValue("US");
    	trial.getStateDropdown().selectByValue("MN");
    	trial.clickSubmitButton();
    	
    	user.customWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("submit-message-text")));
    	user.customWait().until(ExpectedConditions.textToBePresentInElementLocated(By.id("submit-message-text"), "email"));
    	
    	assertTrue("Asserts that a user can sign up for a free trial and that the successful submission message pops up",
    			trial.getElementText(trial.flashNotice).equals("Thank you! You will receive an email confirmation shortly."));

    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    }  
	
	@Test
	@Category(SmokeTests.class)
	public void testStateDropdownAppearsAndDisappears(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		assertTrue(!trial.isStateDropdownVisible());
		
		trial.getCountryDropdown().selectByValue("US");
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.stateDropdown));
		
		assertTrue("Asserts that the State dropdown is visible when the US is selected from the country dropdown",
				trial.isStateDropdownVisible());
		
		trial.getCountryDropdown().selectByValue("AF");
		user.customWait().until(ExpectedConditions.invisibilityOfElementLocated(trial.stateDropdown));
		
		assertTrue("Asserts that the State dropdown is not visible when a country other than the US is selected from the country dropdown",
				!trial.isStateDropdownVisible());

    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));	
	}
	
	@Test
	public void testUsernamePasswordDissappearForNewUser(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
		
		trial.clickExistingUserRadio();
		
		assertTrue("Asserts that Username and Password fields appear when the existing user radio is clicked",
				trial.isElementPresent(trial.administratorUsername) && trial.isElementPresent(trial.administratorPassword));
		
		trial.clickNewUserRadio();
		
		assertTrue("Asserts that Username and Password fields dissaappear when the new user radio is clicked",
				!trial.isElementPresent(trial.administratorUsername) && !trial.isElementPresent(trial.administratorPassword));
		
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));	
	}
	
	@Test
	public void testEmailEmptyMessageAppears(){
    	trial.refresh();
    	user.customWait().until(ExpectedConditions.visibilityOfElementLocated(trial.existingUserRadio));
    	
    	trial.fillOutTrialInformation();
    	trial.clearField(trial.emailField);
    	
    	assertTrue("Asserts that an empty first name field gives the appropriate error",
    			trial.getDriver().findElement(trial.emailField).getAttribute("validationMessage").equals("Please fill out this field."));
	}
}
