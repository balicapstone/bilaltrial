package SuperAdmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class ConfirmationWidget extends BasePage {
	
	public By exitButton = By.className("ui-icon-closethick");
	public By okButton = By.xpath("//*[@class=\"ui-dialog-buttonset\"]/button[1]/span");
	public By cancelButton = By.id("//*[@class=\"ui-dialog-buttonset\"]/button[2]/span");

	public ConfirmationWidget(WebDriver driver){
		pageDriver = driver;
		pageDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
}
