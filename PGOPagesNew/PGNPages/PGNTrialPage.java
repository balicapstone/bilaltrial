package PGNPages;

import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import SharedClasses.BasePage;


public class PGNTrialPage extends BasePage{
	
	public By adminUsernameField = By.id("username");
	public By adminPasswordField = By.id("password");
	public By existingUserRadio = By.id("existing-user");
	public By newUserRadio = By.id("new-user");
	public By emailField = By.id("email");
	public By firstNameField = By.id("name");
	public By lastNameField = By.id("lastname");
	public By roleTitleBy = By.id("role");
	public By institutionTypeBy = By.id("type");
	public By institutionNameField = By.id("institution_name");
	public By institutionZipField = By.id("zip");
	public By addressOneField = By.id("address1");
	public By addressTwoField = By.id("address2");
	public By cityField = By.id("city");
	public By countryBy = By.id("country");
	public By stateBy = By.id("state");
	public By howDidYouHearBy = By.id("reference");
	public By areYouOver18Checkbox = By.id("age");
	public By addMeToEmailCheckbox = By.id("mailing_list");
	public By reviewLicenseAgreementLink = By.linkText("here");
	public By submitTrialFormButton = By.id("new_submit");
	public By zipcodeError = By.id("fb-zip");
	public By emailError = By.id("fb-email");
	public By flashMessage = By.className("flash_message"); //("//*[@id=\"container\"]/div/div[1]");
	
	public Select roleTitleSelect;// = By.id("role");
	public Select institutionTypeSelect; //= By.id("type");
	public Select countrySelect; // = By.id("country");
	public Select stateSelect;
	public Select internationalSelect; // = By.id("state_container");
	public Select howDidYouHearSelect; 
	public By[] initialVisibleElements = {existingUserRadio, newUserRadio, emailField, firstNameField,
			lastNameField, roleTitleBy, institutionTypeBy, institutionNameField,institutionZipField,addressOneField,
			addressTwoField, cityField, countryBy, howDidYouHearBy, areYouOver18Checkbox, 
			addMeToEmailCheckbox, reviewLicenseAgreementLink, submitTrialFormButton};
	public LoginHeader header;
	public FooterMenu footer;
	
	public PGNTrialPage(WebDriver driver){
		pageDriver = driver;
		header = new LoginHeader(pageDriver);
		footer = new FooterMenu(pageDriver);
		roleTitleSelect = new Select(driver.findElement(roleTitleBy));
		institutionTypeSelect = new Select(driver.findElement(institutionTypeBy));
		countrySelect = new Select(driver.findElement(countryBy));
		howDidYouHearSelect = new Select(driver.findElement(howDidYouHearBy));
		stateSelect = new Select(driver.findElement(stateBy));
	}
	
	public PGNTrialPage AdministratorLogin(){
		pageDriver.findElement(adminUsernameField).sendKeys("test");
		return this;
	}
	
	/**
	 * Clicks the New Trial submit button with one or more fields not filled out.
	 */
	public PGNTrialPage clickSubmitButton(){
		pageDriver.findElement(submitTrialFormButton).click();
		return this;
	}
	
	public PGNTrialPage clickOverEighteenCheckbox(){
		pageDriver.findElement(areYouOver18Checkbox).click();
		return this;
	}
	
	public PGNTrialPage clickExistingUserRadio(){
		click(existingUserRadio);
		return this;
	}
	
	public PGNTrialPage clickNewUserRadio(){
		click(newUserRadio);
		return this;
	}
	
	
	/*
	 * Fills out a generic set of trial information.
	 */
	public PGNTrialPage fillOutTrialInformation(){
		Date date = new Date();
		String time = new Timestamp(date.getTime()).toString().substring(11, 23).replace(":", "");
		pageDriver.findElement(emailField).sendKeys("Test"+time+"@test.com");
		pageDriver.findElement(firstNameField).sendKeys("Test");
		pageDriver.findElement(lastNameField).sendKeys("Guy");
		getRoleDropdown().selectByValue("Other");
		getTypeDropdown().selectByValue("Other");
		pageDriver.findElement(institutionNameField).sendKeys("Test School");
		pageDriver.findElement(institutionZipField).sendKeys("55414");
		pageDriver.findElement(addressOneField).sendKeys("111 Fake Street");
		pageDriver.findElement(cityField).sendKeys("Testville");
		getCountryDropdown().selectByValue("US");
		getStateDropdown().selectByValue("MN");
		getHowDidYouHearDropdown().selectByValue("Other");
		pageDriver.findElement(areYouOver18Checkbox).click();
		return this;
	}
	
	public PGNTrialPage fillOutTrialInformation(String email){
		pageDriver.findElement(emailField).sendKeys(email);
		pageDriver.findElement(firstNameField).sendKeys("Test");
		pageDriver.findElement(lastNameField).sendKeys("Test");
		getRoleDropdown().selectByValue("Other");
		getTypeDropdown().selectByValue("Other");
		pageDriver.findElement(institutionNameField).sendKeys("Test School");
		pageDriver.findElement(institutionZipField).sendKeys("55414");
		pageDriver.findElement(addressOneField).sendKeys("111 Fake Street");
		pageDriver.findElement(cityField).sendKeys("Testville");
		getCountryDropdown().selectByValue("US");
		getStateDropdown().selectByValue("MN");
		getHowDidYouHearDropdown().selectByValue("Other");
		pageDriver.findElement(areYouOver18Checkbox).click();
		return this;
	}
	
	
	/* Set of elements getters */
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public By getAdministratorUsername(){
		return adminUsernameField;
	}
	
	public By getAdministratorPassword(){
		return adminPasswordField;
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
		return new Select(pageDriver.findElement(roleTitleBy));
	}
	
	public Select getTypeDropdown(){
		return new Select(pageDriver.findElement(institutionTypeBy));
	}
	
	public By getInstitutionNameField(){
		return institutionNameField;
	}
	
	public By getZipField(){
		return institutionZipField;
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
	
	public By getEmailError(){
		return emailError;
	}
	
	public Select getCountryDropdown(){
		return countrySelect;
	}
	
	public Select getStateDropdown(){
		return stateSelect;
	}
	
	public Select getHowDidYouHearDropdown(){
		return howDidYouHearSelect;
	}
	
	public By getOverEighteenCheckbox(){
		return areYouOver18Checkbox;
	}
	
	public By getMailingListCheckbox(){
		return addMeToEmailCheckbox;
	}
	
	public By getNewUserSubmitButton(){
		return submitTrialFormButton;
	}
	
	public By getInstitutionZipFieldError(){
		return zipcodeError;
	}
	
	public By getFlashNotice(){
		return flashMessage;
	}
	
	public boolean isCountryDropdownVisible(){
		return isElementPresent(countryBy);
	}
	
	public boolean isStateDropdownVisible(){
		return isElementPresent(stateBy);
	}
	
	public boolean isExistingUsernameFieldVisible(){
		return isElementPresent(adminUsernameField);
	}
	
	public boolean isExistingPasswordFieldVisible(){
		return isElementPresent(adminPasswordField);
	}
	
	public boolean isMessagePresent(){
		return isElementPresent(flashMessage);
	}
}
