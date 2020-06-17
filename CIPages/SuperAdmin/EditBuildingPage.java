package SuperAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class EditBuildingPage extends BasePage{
	public By editAdminUsername = By.id("building-admin-username");
	public By editAdminPassword = By.id("building-admin-password");
	public By editStudentUsername = By.id("building-student-username");
	public By editStudentPassword = By.id("building-student-password");
	public By studentSQS = By.id("building-student-sqs");
	public By saveButton = By.xpath("//input[@value=\"Save\"]");
	public By message = By.xpath("//span[@class=\"message-txt\"]");
	
	public EditBuildingPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public void editAdminUsername(String username){
		clearField(editAdminUsername);
		sendKeysToElement(username, editAdminUsername);
	}
	
	public void editAdminPassword(String password){
		clearField(editAdminPassword);
		sendKeysToElement(password, editAdminPassword);
	}
	
	public void editStudentUsername(String username){
		clearField(editStudentUsername);
		sendKeysToElement(username, editStudentUsername);
	}
	
	public void editStudentPassword(String password){
		clearField(editStudentPassword);
		sendKeysToElement(password, editStudentPassword);
	}
	
	public void clickSave(){
		click(saveButton);
	}
}
