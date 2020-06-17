package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import SharedClasses.BasePage;

public class UserModal extends BasePage{
	public By userSelect = By.id("userSelect");
	public By username = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[2]/input");
	public By password = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[3]/input");
	public By firstName = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[4]/input");
	public By lastName = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[5]/input");
	public By buildingName = By.id("buildingSelect");
	
	public By appDropdown = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[7]/div[1]/select");
	public By teacherAccount = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[7]/div[3]/input");
	public By ciUser = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[7]/div[5]/input");
	public By pgoUser = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[7]/div[6]/input");
	public By pgnUser = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/div[7]/div[7]/input");
	public By createNewUserButton = By.xpath("/html/body/div/div[5]/div[2]/div[2]/div/button");
	
	public UserModal(WebDriver driver){
		pageDriver = driver;
	}
	
	public UserModal selectUserType(String type){
		int numb = pageDriver.findElements(userSelect).size();
		
		Select userType = new Select(pageDriver.findElements(userSelect).get(numb-1));
		userType.selectByVisibleText(type);
		return this;
	}
	
	public UserModal selectAppType(String app){
		int numb = pageDriver.findElements(appDropdown).size();
		
		Select appType = new Select(pageDriver.findElements(appDropdown).get(numb-1));
		appType.selectByVisibleText(app);
		return this;
	}
	
	public UserModal enterUsername(String user){
		clearField(username);
		sendKeysToElement(user, username);
		return this;
	}
	
	public UserModal enterPassword(String pass){
		clearField(password);
		sendKeysToElement(pass, password);
		return this;
	}
	
	public UserModal enterFirstName(String first){
		clearField(firstName);
		sendKeysToElement(first, firstName);
		return this;
	}
	
	public UserModal enterlastName(String last){
		clearField(lastName);
		sendKeysToElement(last, lastName);
		return this;
	}
	
	public UserModal selectBuildingName(String building){
		int numb = pageDriver.findElements(buildingName).size();
		Select buildingList = new Select(pageDriver.findElements(buildingName).get(numb-1));
		buildingList.selectByVisibleText(building);
		return this;
	}
	
	public UserModal enterTeacherName(String teacher){
		clearField(teacherAccount);
		sendKeysToElement(teacher, teacherAccount);
		return this;
	}
	
	public UserModal enterCIUsername(String ci){
		clearField(ciUser);
		sendKeysToElement(ci, ciUser);
		return this;
	}
	
	public UserModal enterPGOUsername(String pgo){
		clearField(pgoUser);
		sendKeysToElement(pgo, pgoUser);
		return this;
	}
	
	public UserModal enterPGNUsername(String pgn){
		clearField(pgnUser);
		sendKeysToElement(pgn, pgnUser);
		return this;
	}
	
	public UserModal createNewUserError(){
		click(createNewUserButton);
		return this;
	}
	
	public UserView createNewUser(){
		click(createNewUserButton);
		return new UserView(pageDriver);
	}
}
