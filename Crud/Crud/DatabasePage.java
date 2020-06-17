package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class DatabasePage extends BasePage{
	
	protected By logout = By.id("logout");
	protected By back = By.xpath("/html/body/div/a/button");
	protected By searchField = By.id("text-search");
	
	
	public DatabasePage(WebDriver driver){
		pageDriver = driver;
	}
	
	public CrudLogin logout(){
		click(logout);
		return new CrudLogin(pageDriver);
	}
	
	public DatabasePage goBackInDatabase(){
		click(back);
		return this;
	}
	
	public DatabaseSelect goBackToSelectPage(){
		click(back);
		return new DatabaseSelect(pageDriver);
	}
	
	public DatabasePage searchDB(String search){
		clearField(searchField);
		sendKeysToElement(search, searchField);
		return this;
	}
	
}
