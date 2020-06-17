package PGOPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;
import SharedClasses.ThirdPartyPage;

public class AboutUsPage extends BasePage{
	private By capstoneImage = By.xpath("//*[@id=\"content\"]/div[1]/p[2]/a/img");
	private By supportEmailLink = By.linkText("support@capstonepub.com");
	private By pebbleGoLink = By.linkText("http://www.pebblego.com");
	private By[] initialVisibleElements = {capstoneImage, supportEmailLink, pebbleGoLink};
	public NavigationBar navBar;
	
	public AboutUsPage(WebDriver driver){
		pageDriver = driver;
		navBar = new NavigationBar(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	/*
	 * Class click methods
	 */
	public ThirdPartyPage clickCapstoneLogo(){
		click(capstoneImage);
		return new ThirdPartyPage(pageDriver);
	}
	
	public LoginPage clickPebbleGoLink(){
		click(pebbleGoLink);
		return new LoginPage(pageDriver);
	}
}
