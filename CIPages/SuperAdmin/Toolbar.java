package SuperAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class Toolbar extends BasePage{
	public By dashboardButton = By.linkText("Dashboard");
	public By superUsersButton = By.linkText("Super Users");
	public By accountListButton = By.xpath("//div[@class=\"menu-main\"]/ul/li[3]/a"); //By.linkText("Account list");
	public By buildingListButton = By.linkText("Building List");
	public By orderHistoryButton = By.linkText("Order History");
	public By reportsButton = By.linkText("Reports");
	public By ipRanges = By.linkText("IP Ranges");
	
	public Toolbar(WebDriver driver){
		pageDriver = driver;
	}
	
	public OrderHistoryPage clickOrderHistoryTab(){
		click(orderHistoryButton);
		return new OrderHistoryPage(pageDriver);
	}
	
	public BuildingListPage clickBuildingListTab(){
		click(buildingListButton);
		return new BuildingListPage(pageDriver);
	}
	
	public AccountListPage clickAccountListTab(){
		click(accountListButton);
		return new AccountListPage(pageDriver);
	}
}
