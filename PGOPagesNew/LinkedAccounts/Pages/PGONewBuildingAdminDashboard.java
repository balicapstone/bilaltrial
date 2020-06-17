package LinkedAccounts.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import SharedClasses.PGONewBasePage;
import UserClasses.User;

public class PGONewBuildingAdminDashboard extends PGONewBasePage{
	public By eulaWarningLink = By.xpath("/html/body/header/div[3]/p/span[2]/a");
	public By dashboardTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[1]/a");
	public By reportsTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[2]/span");
	public By studentDisplayOptions = By.xpath("/html/body/header/div[2]/div/div/ul/li[3]/a");
	public By booksAndBooklists = By.xpath("/html/body/header/div[2]/div/div/ul/li[4]/span");
	public By linkAccountsTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[5]/a");
	public By openStudentView = By.xpath("/html/body/header/div[1]/div[1]/a");
	public By adminButton = By.xpath("//li[@class=\"user-menu-li\"]/span");
	public By editMyProfile = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[1]/a");
	public By logout = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[2]/a");
	public By[] initialVisibleElements = {dashboardTab, reportsTab, studentDisplayOptions, booksAndBooklists, linkAccountsTab,
			openStudentView, adminButton};
	
	public PGONewBuildingAdminDashboard(User u){
		user = u;
		pageDriver = u.getDriver();
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public PGONewCILoginPage logout(){
		click(adminButton);
		waitImplicitly(1);
		click(logout);
		return new PGONewCILoginPage(pageDriver);
	}
	
	public PGONewBuildingAdminDashboard clickDashboardTab(){
		click(dashboardTab);
		return new PGONewBuildingAdminDashboard(user);
	}
	
	public PGONewLinkAccountsTab clickLinkAccountsTab(){
		click(linkAccountsTab);
		return new PGONewLinkAccountsTab(user);
	}
	
	public void openStudentView(){
		click(openStudentView);
	}
}
