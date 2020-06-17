package MasterAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Crud.CrudLogin;
import SharedClasses.BasePage;

public class UsersTable extends BasePage{

	private By logout = By.id("logout");
	//private By backButton = By.xpath("/html/body/div/a/button");
	//private By newUser = By.xpath("/html/body/div/div[2]/button");
	//private By searchField = By.id("text-search");
	//private By filterByRole = By.id("userSelect");
	
	
	public UsersTable(WebDriver driver){
		pageDriver = driver;
	}
	
	public CrudLogin logout(){
		click(logout);
		return new CrudLogin(pageDriver);
	}
}
