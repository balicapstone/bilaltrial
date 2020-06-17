package BuildingAdmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;


public class EULAAcceptancePage extends BasePage{
	public By allRecordsCheckBox = By.id("order-all");
	public By AllRecordsText = By.className("eula-select-all");
	public By acceptButton = By.id("bttn-accept");
	public By flashMessage = By.id("message-txt");
	public By pleaseSelectAnOrderMessage = By.xpath("/html/body/div[2]/div/form/div/p");//("/html/body/div[2]/div/form/div/p");
	//By.className("eula-select-all");
			//By.id("bttn-accept");
	
	
	public EULAAcceptancePage(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public BuildingAdminDashboard clickAcceptButton(){
		click(acceptButton);
		return new BuildingAdminDashboard(pageDriver);
	}
}
