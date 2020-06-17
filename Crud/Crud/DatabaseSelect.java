package Crud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import SharedClasses.BasePage;

public class DatabaseSelect extends BasePage{

	public By logout = By.id("logout");
	public By districtsButton = By.xpath("/html/body/div/a[1]/button");
	public By logo = By.id("logo");
	public By usersButton = By.xpath("/html/body/div/a[2]/button");
	
	public DatabaseSelect(WebDriver driver){
		pageDriver = driver;
	}
	
	public CrudLogin logout(){
		click(logout);
		return new CrudLogin(pageDriver);
	}
	
	public DistrictsView goToDistrictsDatabase(){
		click(districtsButton);
		return new DistrictsView(pageDriver);
	}
	
	public UserView goToUsersDatabese(){
		click(usersButton);
		return new UserView(pageDriver);
	}
}
