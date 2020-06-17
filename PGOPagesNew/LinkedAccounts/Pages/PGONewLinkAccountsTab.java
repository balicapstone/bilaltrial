package LinkedAccounts.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SharedClasses.PGONewBasePage;
import SharedClasses.PGONewGooglePage;
import UserClasses.User;

public class PGONewLinkAccountsTab extends PGONewBasePage{
	public By linkAccountsButton = By.xpath("/html/body/div/div/a[1]");
	public By linkGoogleToAccountButton = By.xpath("/html/body/div/div/a[2]");
	public By linkGoogleAccountError = By.xpath("/html/body/div/div/p[5]");
	public By adminButton = By.xpath("//li[@class=\"user-menu-li\"]/span");
	public By editMyProfile = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[1]/a");
	public By logout = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[2]/a");
	
	public PGONewLinkAccountsTab(User u){
		user = u;
		pageDriver = u.getDriver();
	}
	
	public PGONewTeacherLogin clickLinkAccountsButton(){
		String originalWindow = user.getDriver().getWindowHandle();
		
		click(linkAccountsButton);
		user.customWait().until(ExpectedConditions.numberOfWindowsToBe(2));
		
		this.SwitchToNewPage(originalWindow);
		
		return new PGONewTeacherLogin(user);
	}
	
	public PGONewGooglePage clickLinkGoogleToAccountButton(){
		click(linkGoogleToAccountButton);
		return new PGONewGooglePage(user);
	}
}
