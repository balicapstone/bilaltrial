package SuperAdmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class SuperAdminHomePage extends BasePage{
	public By newOrderButton = By.linkText("New Order");
	public Toolbar toolbar;
	
	public SuperAdminHomePage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		toolbar = new Toolbar(pageDriver);
	}
	
	public AccountListPage navigateToAccountList(String environment){
		pageDriver.get("https://"+environment+".mycapstonelibrary.com/superadmin/account/list");
		return new AccountListPage(pageDriver);
	}
}
