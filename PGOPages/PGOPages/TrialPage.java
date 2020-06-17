package PGOPages;

import org.openqa.selenium.support.ui.Select;

import SharedClasses.BasePage;

import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrialPage extends BasePage{
	
	//public String[] elementsToCheck = {"administratorUsername", "administratorPassword"};
	private By administratorUsername = By.id("username");
	private By administratorPassword = By.id("password");
	public By existingUserRadio = By.id("existing-user");
	public By newUserRadio = By.id("new-user");
	public By existingRecordWarning = By.className("flashwarning");
	private By emailField = By.id("email");
	public By firstNameField = By.id("name");
	private By lastNameField = By.id("lastname");
	private By roleDropdown = By.id("role");
	private By typeDropdown = By.id("type");
	private By institutionNameField = By.id("institution_name");
	private By zipField = By.id("zip");
	private By addressOneField = By.id("address1");
	private By addressTwoField = By.id("address2");
	private By cityField = By.id("city");
	private By countryDropdown = By.id("country");
	private By stateDropdown = By.id("state");
	private By referenceDropdown = By.id("reference");
	private By overEighteenCheckbox = By.id("age");
	private By mailingListCheckbox = By.id("mailing_list");
	private By submitButton = By.id("new_submit");
	private By emailFieldError = By.id("fb-email");
	//private By nameFieldError = By.id("fb-name"); commenting out because this error message no long appears and we've agreed the field highlighting is sufficient warning
	private By roleDropdownError = By.id("fb-role");
	private By institutionTypeDropdownError = By.id("fb-type");
	private By institutionNameFieldError = By.id("fb-institution");
	private By institutionZipFieldError = By.id("fb-zip");
	private By addressOneFieldError = By.id("fb-address1");
	private By cityFieldError = By.id("fb-city");
	private By stateDropdownError = By.id("fb-state");
	private By referenceDropdownError = By.id("fb-reference");
	private By overEighteenCheckboxError = By.id("fb-age"); 
	public By flashNotice = By.xpath("//*[@id='content']/div[contains(@class, 'flash_message')]");//*[@id="content"]/div[1]
	private By[] initialVisibleElements = {existingUserRadio, newUserRadio, emailField, firstNameField, lastNameField, roleDropdown, typeDropdown, institutionNameField, zipField, addressOneField, addressTwoField, 
			cityField, 
			referenceDropdown, overEighteenCheckbox, mailingListCheckbox, submitButton};
	private By[] emptyTrialInfoErrorMessages = {emailFieldError, roleDropdownError, institutionTypeDropdownError,
			institutionNameFieldError, institutionZipFieldError, addressOneFieldError, cityFieldError,
			referenceDropdownError, overEighteenCheckboxError};
	private By[] allErrorsBesideOverEighteen = {roleDropdownError, institutionTypeDropdownError,
			institutionNameFieldError, addressOneFieldError, cityFieldError,
			referenceDropdownError};
	public NavigationBar navBar;
	
	public TrialPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
	}
	
	public TrialPage AdministratorLogin(){
		pageDriver.findElement(administratorUsername).sendKeys("test");
		return this;
	}
	
	/**
	 * Clicks the New Trial submit button with one or more fields not filled out.
	 */
	public TrialPage clickSubmitButton(){
		click(submitButton);
		return this;
	}
	
	public TrialPage clickOverEighteenCheckbox(){
		pageDriver.findElement(overEighteenCheckbox).click();
		return this;
	}
	
	public TrialPage clickExistingUserRadio(){
		click(existingUserRadio);
		return this;
	}
	
	public TrialPage clickNewUserRadio(){
		click(newUserRadio);
		return this;
	}
	
	/*
	 * Fills out a generic set of trial information.
	 */
	public TrialPage fillOutTrialInformation(){
		Date date = new Date();
		String time = new Timestamp(date.getTime()).toString().substring(11, 23).replace(":", "");
		sendKeysClean("Test"+time+"@test.com",emailField);
		sendKeysClean("Test", firstNameField);
		sendKeysClean("Test", lastNameField);
		getRoleDropdown().selectByValue("Other");
		getTypeDropdown().selectByValue("Other");
		sendKeysClean("Test School", institutionNameField);
		sendKeysClean("55414", zipField);
		sendKeysClean("111 Fake Street", addressOneField);
		sendKeysClean("Testville", cityField);
		getCountryDropdown().selectByValue("US");
		getStateDropdown().selectByValue("MN");
		getReferenceDropdown().selectByValue("Other");
		click(overEighteenCheckbox);
		return this;
	}
	
	public TrialPage fillOutTrialInformation(String email){
		sendKeysClean(email, emailField);
		sendKeysClean("Test", firstNameField);
		sendKeysClean("Test", lastNameField);
		getRoleDropdown().selectByValue("Other");
		getTypeDropdown().selectByValue("Other");
		sendKeysClean("Test School", institutionNameField);
		sendKeysClean("55414", zipField);
		sendKeysClean("111 Fake Street", addressOneField);
		sendKeysClean("Testville", cityField);
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
	
	public By getAdministratorUsername(){
		return administratorUsername;
	}
	
	public By getAdministratorPassword(){
		return administratorPassword;
	}

	public By getExistingRecordWarning(){
		return existingRecordWarning;
	}
	
	public By getEmailField(){
		return emailField;
	}
	
	public By getFirstNameField(){
		return firstNameField;
	}
	
	public By getLastNameField(){
		return lastNameField;
	}
	
	public Select getRoleDropdown(){
		return new Select(pageDriver.findElement(roleDropdown));
	}
	
	public Select getTypeDropdown(){
		return new Select(pageDriver.findElement(typeDropdown));
	}
	
	public By getInstitutionNameField(){
		return institutionNameField;
	}
	
	public By getZipField(){
		return zipField;
	}
	
	public By getAddressOneField(){
		return addressOneField;
	}
	
	public By getAddressTwoField(){
		return addressTwoField;
	}
	
	public By getCityField(){
		return cityField;
	}
	
	public Select getCountryDropdown(){
		return new Select(pageDriver.findElement(countryDropdown));
	}
	
	public Select getStateDropdown(){
		return new Select(pageDriver.findElement(stateDropdown));
	}
	
	public Select getReferenceDropdown(){
		return new Select(pageDriver.findElement(referenceDropdown));
	}
	
	public By getOverEighteenCheckbox(){
		return overEighteenCheckbox;
	}
	
	public By getMailingListCheckbox(){
		return mailingListCheckbox;
	}
	
	public By getEmailFieldError(){
		return emailFieldError;
	}
	
	public By getRoleDropdownError(){
		return roleDropdownError;
	}
	
	public By getInstitutionTypeDropdownError(){
		return institutionTypeDropdownError;
	}
	
	public By getInstitutionNameFieldError(){
		return institutionNameFieldError;
	}
	
	public By getInstitutionZipFieldError(){
		return institutionZipFieldError;
	}
	
	public By getAddressOneFieldError(){
		return addressOneFieldError;
	}
	
	public By getCityFieldError(){
		return cityFieldError;
	}
	
	public By getStateDropdownError(){
		return stateDropdownError;
	}
	
	public By getReferenceDropdownError(){
		return referenceDropdownError;
	}
	
	public By getOverEighteenCheckboxError(){
		return overEighteenCheckboxError;
	}
	
	public By getFlashNotice(){
		return flashNotice;
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
