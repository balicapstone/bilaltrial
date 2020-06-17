package PGNPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import SharedClasses.BasePage;

public class PGNAboutUsPage extends BasePage{
	public By capstoneLogo = By.xpath("//*[@id=\"text-container\"]/div[2]/p[2]/a");
	public By supportEmailLink = By.linkText("support@capstonepub.com");
	public By websiteLink = By.linkText("http://www.pebblegonext.com");
	public FooterMenu footer;
	public LoginHeader header;
	public By[] initialVisibleElements = {capstoneLogo, supportEmailLink, websiteLink};
	
	public PGNAboutUsPage(WebDriver driver){
		pageDriver = driver;
		footer = new FooterMenu(driver);
		header = new LoginHeader(driver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
}
