package PGOPagesNew;

import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewTrialPage extends PGONewBasePage{
	public By administratorUsername = By.id("username");
	public By administratorPassword = By.id("password");
	public By existingUserRadio = By.id("existing-user");
	public By newUserRadio = By.id("new-user");
	public By existingRecordWarning = By.className("flashwarning");
	public By emailField = By.id("email");
	public By firstNameField = By.id("firstname");
	public By lastNameField = By.id("lastname");
	public By roleDropdown = By.id("role");
	public By typeDropdown = By.id("type");
	public By institutionNameField = By.id("institution-name");
	public By zipField = By.id("institution-zip");
	public By addressOneField = By.id("address1");
	public By addressTwoField = By.id("address2");
	public By cityField = By.id("city");
	public By countryDropdown = By.id("country-select");
	public By stateDropdown = By.id("state-select");
	public By howDidYouHearDropdown = By.id("how-did-you-hear-select");
	public By overEighteenCheckbox = By.id("age");
	public By mailingListCheckbox = By.id("add-me");
	public By licenseAgreementLink = By.linkText("license agreement");
	public By submitButton = By.id("submit-button");
	public By emailFieldError = By.id("fb-email"); //*[@id="root"]/div/main/form/div[2]/div[2]
	public By roleDropdownError = By.id("fb-role");
	public By institutionTypeDropdownError = By.id("fb-type");
	public By institutionNameFieldError = By.id("fb-institution");
	public By institutionZipFieldError = By.id("fb-zip");
	public By addressOneFieldError = By.id("fb-address1");
	public By cityFieldError = By.id("fb-city");
	public By stateDropdownError = By.id("fb-state");
	public By referenceDropdownError = By.id("fb-reference");
	public By overEighteenCheckboxError = By.id("fb-age"); 
	public By flashNotice = By.id("submit-message-text");
	public By[] initialVisibleElements = {existingUserRadio, newUserRadio, emailField, firstNameField, lastNameField, roleDropdown, typeDropdown, institutionNameField, zipField, addressOneField, addressTwoField, 
			cityField, howDidYouHearDropdown, overEighteenCheckbox, mailingListCheckbox, submitButton, licenseAgreementLink};
	public By[] emptyTrialInfoErrorMessages = {emailFieldError, roleDropdownError, institutionTypeDropdownError,
			institutionNameFieldError, institutionZipFieldError, addressOneFieldError, cityFieldError,
			referenceDropdownError, overEighteenCheckboxError};
	public By[] allErrorsBesideOverEighteen = {roleDropdownError, institutionTypeDropdownError,
			institutionNameFieldError, addressOneFieldError, cityFieldError,
			referenceDropdownError};
	
	public PGONewTrialPage(User u){
		pageDriver = u.getDriver();
		
		if(u.getEnvironment().equals("reactqa")){
			pageDriver.get("https://trialformqa.pebblego.com/");
		}else if(u.getEnvironment().equals("qa")){
			pageDriver.get("https://trialformqa.pebblego.com/");
		}else if(u.getEnvironment().equals("reactstaging")){
			pageDriver.get("https://trialformstaging.pebblego.com/");
		}else if(u.getEnvironment().equals("staging")){
			pageDriver.get("https://trialformstaging.pebblego.com/");
		}else if(u.getEnvironment().equals("site")){
			pageDriver.get("https://trialform.pebblego.com/");
		}
	}
	
	public PGONewTrialPage AdministratorLogin(){
		pageDriver.findElement(administratorUsername).sendKeys("test");
		return this;
	}
	
	/**
	 * Clicks the New Trial submit button with one or more fields not filled out.
	 */
	public PGONewTrialPage clickSubmitButton(){
		click(submitButton);
		return this;
	}
	
	public PGONewTrialPage clickOverEighteenCheckbox(){
		pageDriver.findElement(overEighteenCheckbox).click();
		return this;
	}
	
	public PGONewTrialPage clickExistingUserRadio(){
		click(existingUserRadio);
		return this;
	}
	
	public PGONewTrialPage clickNewUserRadio(){
		click(newUserRadio);
		return this;
	}
	
	/*
	 * Fills out a generic set of trial information.
	 */
	public PGONewTrialPage fillOutTrialInformation(){
		Date date = new Date();
		String time = new Timestamp(date.getTime()).toString().substring(11, 22).replace(":", "").replace(".", "");
		sendKeysToElement("Test"+time+"@test.com",emailField);
		sendKeysToElement("Test", firstNameField);
		sendKeysToElement("Test", lastNameField);
		getRoleDropdown().selectByValue("Teacher");
		getTypeDropdown().selectByValue("School District");
		sendKeysToElement("Test School", institutionNameField);
		sendKeysToElement("55414", zipField);
		sendKeysToElement("111 Fake Street", addressOneField);
		sendKeysToElement("Testville", cityField);
		getCountryDropdown().selectByValue("US");
		getStateDropdown().selectByValue("MN");
		getReferenceDropdown().selectByValue("Other");
		click(overEighteenCheckbox);
		return this;
	}
	
	public PGONewTrialPage fillOutTrialInformation(String email){
		sendKeysToElement(email, emailField);
		sendKeysToElement("Test", firstNameField);
		sendKeysToElement("Test", lastNameField);
		getRoleDropdown().selectByValue("Teacher");
		getTypeDropdown().selectByValue("School District");
		sendKeysToElement("Test School", institutionNameField);
		sendKeysToElement("55414", zipField);
		sendKeysToElement("111 Fake Street", addressOneField);
		sendKeysToElement("Testville", cityField);
		getCountryDropdown().selectByValue("US");
		getStateDropdown().selectByValue("MN");
		getReferenceDropdown().selectByValue("Other");
		pageDriver.findElement(overEighteenCheckbox).click();
		return this;
	}
	
	
	/* Set of elements getters */
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By[] getEmptyTrialInfoErrorMessages(){
		return emptyTrialInfoErrorMessages;
	}
	
	public By[] getAllErrorsBesideOverEighteen(){
		return allErrorsBesideOverEighteen;
	}
	
	public Select getRoleDropdown(){
		return new Select(pageDriver.findElement(roleDropdown));
	}
	
	public Select getTypeDropdown(){
		return new Select(pageDriver.findElement(typeDropdown));
	}
	
	public Select getCountryDropdown(){
		return new Select(pageDriver.findElement(countryDropdown));
	}
	
	public Select getStateDropdown(){
		return new Select(pageDriver.findElement(stateDropdown));
	}
	
	public Select getReferenceDropdown(){
		return new Select(pageDriver.findElement(howDidYouHearDropdown));
	}
	
	public String getFlashNoticeText(){
		return pageDriver.findElement(flashNotice).getText();
	}
	
	public boolean isCountryDropdownVisible(){
		return isElementPresent(countryDropdown);
	}
	
	public boolean isStateDropdownVisible(){
		return isElementPresent(stateDropdown);
	}
	
	public boolean isExistingUsernameFieldVisible(){
		return isElementPresent(administratorUsername);
	}
	
	public boolean isExistingPasswordFieldVisible(){
		return isElementPresent(administratorPassword);
	}
	
	public boolean isMessagePresent(){
		try{
			pageDriver.findElement(flashNotice);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
