package BuildingAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class EditMyProfileBuildingAdminPage extends BasePage{
	public By dashboardTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[1]/a");
	public By reportsTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[2]/span");
	public By studentDisplayOptions = By.xpath("/html/body/header/div[2]/div/div/ul/li[3]/a");
	public By booksAndBooklists = By.xpath("/html/body/header/div[2]/div/div/ul/li[4]/span");
	public By linkAccountsTab = By.xpath("/html/body/header/div[2]/div/div/ul/li[5]/a");
	public By openStudentView = By.xpath("/html/body/header/div[1]/div[1]/a");
	public By adminButton = By.xpath("//li[@class=\"user-menu-li\"]/span");
	public By editMyProfile = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[1]/a");
	public By logout = By.xpath("//li[@class=\"user-menu-li\"]/ul/li[2]/a");
	public By dashboardBreadcrumb = By.xpath("//div[@class=\"breadcrumb\"]/a");
	public By adminUsername = By.id("admin_username");
	public By adminPassword = By.id("admin_password");
	public By saveButton = By.xpath("//input[@value=\"Save\"]");
	public By cancelButton = By.id("bttn-cancel");
	public By[] initialVisibleElements = {dashboardTab, reportsTab, studentDisplayOptions, booksAndBooklists, linkAccountsTab,
			openStudentView, adminButton, adminUsername, dashboardBreadcrumb, adminPassword, saveButton, cancelButton};
	
	
	public EditMyProfileBuildingAdminPage(WebDriver driver){
		pageDriver = driver;
	}
	
	public void changePassword(String password){
		clearField(adminPassword);
		sendKeysToElement(password, adminPassword);
	}
	
	public void changeUsername(String username){
		clearField(adminUsername);
		sendKeysToElement(username, adminUsername);
	}
	
	public BuildingAdminDashboard clickDashboardBreadcrumb(){
		click(dashboardBreadcrumb);
		return new BuildingAdminDashboard(pageDriver);
	}
	
	public void clickSave(){
		click(saveButton);
	}
	
	public BuildingAdminDashboard clickCancel(){
		click(cancelButton);
		return new BuildingAdminDashboard(pageDriver);
	}
	
}
