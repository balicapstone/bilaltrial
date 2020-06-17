package PGOPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class CopyrightPage extends BasePage{
	public By pebbleGoEmailLink = By.linkText("support@capstonepub.com");
	public NavigationBar navBar;
	
	public CopyrightPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
	}
	
	/**
	 * Checks that the PebbleGo email link is displayed.
	 * @return boolean
	 */
	public boolean checkPebbleGoEmailLink(){
		return pageDriver.findElement(pebbleGoEmailLink).isDisplayed();
	}
}
