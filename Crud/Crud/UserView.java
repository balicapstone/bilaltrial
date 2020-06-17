package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UserView extends DatabasePage{
	private By createNewUserButton = By.xpath("/html/body/div/div[2]/button");
	private By filterByRole = By.id("userSelect");
	
	public UserView(WebDriver driver){
		super(driver);
	}
	
	public UserModal createNewUser(){
		click(createNewUserButton);
		return new UserModal(pageDriver);
	}
	
	/*
	 * select from Student, Teacher, Building Administrator, District Administrator, SuperAdmin, Student Master Account, Show All
	 */
	public UserView filterByRole(String role){
		Select filter = new Select(pageDriver.findElement(filterByRole));
		filter.selectByValue(role);
		return this;
	}
}
