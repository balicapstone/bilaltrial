package BuildingAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import MasterAccount.TeacherLogin;
import SharedClasses.BasePage;
import SharedClasses.GooglePage;

public class LinkAccountsTab extends BasePage{
	public By linkAccountsButton = By.xpath("/html/body/div/div/a[1]");
	public By linkGoogleToAccountButton = By.xpath("/html/body/div/div/a[2]");
	public By linkGoogleAccountError = By.xpath("/html/body/div/div/p[5]");
	public By adminButton = By.xpath("//li[@class=\"user-menu-li\"]/span");
	public By editMyProfile = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[1]/a");
	public By logout = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[2]/a");
	
	public LinkAccountsTab(WebDriver driver){
		pageDriver = driver;
	}
	
	public TeacherLogin clickLinkAccountsButton(){
		click(linkAccountsButton);
		return new MasterAccount.TeacherLogin(pageDriver);
	}
	
	public GooglePage clickLinkGoogleToAccountButton(){
		click(linkGoogleToAccountButton);
		return new GooglePage(pageDriver);
	}
}
